import javax.persistence.*;
import java.util.Date;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;
    private String aname;

    private int age;

    private Date data;

    private String typeOfAnimal;
    @ManyToOne
    private Zoo zoo;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }
    Animal(){
        this.data=new Date();
    }
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }




    @Override
    public String toString() {
        return "Animal{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", data=" + data +
                ", typeOfAnimal='" + typeOfAnimal + '\'' +
                ", zoo=" + zoo +
                '}';
    }
}
