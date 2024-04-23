package EJBs;

import javax.persistence.*;


@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int number1;
    private int number2;
    private String operation;

    private int result;

    public Calculation() {
    }

    public Calculation(int number1, int number2, String operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
    }

    // Getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int calculate() {
        switch (operation) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 != 0) {
                    return number1 / number2;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }

    public void save() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}
