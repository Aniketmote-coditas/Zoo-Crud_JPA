import javax.persistence.*;
import java.util.List;

@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int zid;
    private String zname;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "zoo")
    private List<Animal> animal;

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "zid=" + zid +
                ", zname='" + zname + '\'' +
                ", animal=" + animal +
                '}';
    }
}
