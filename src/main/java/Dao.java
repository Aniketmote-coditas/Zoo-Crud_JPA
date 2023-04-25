import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    public static void insert() throws IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the name of the Zoo");
        String zname = bufferedReader.readLine();


        Zoo zoo = new Zoo();
        zoo.setZname(zname);
        entityManager.getTransaction().begin();
        entityManager.persist(zoo);
        entityManager.getTransaction().commit();

        System.out.println("Enter the number of Animal you want to register in this Zoo");
        int nuberOfAnimal = Integer.parseInt(bufferedReader.readLine());
        List<Animal> list = new ArrayList<>();
        while (nuberOfAnimal-->0){

            System.out.println("Enter the name of Animal");
            String aname = bufferedReader.readLine();
            System.out.println("Enter the age of animal");
            int age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the type of Animal\nEnter W for Wild and D for Domestic");
            String typeOfAnimal = bufferedReader.readLine();

            Animal animal = new Animal();
            animal.setTypeOfAnimal(typeOfAnimal);
            animal.setAname(aname);
            animal.setZoo(zoo);
            animal.setAge(age);





            list.add(animal);
            zoo.setAnimal(list);

            entityManager.getTransaction().begin();
            entityManager.persist(zoo);
            entityManager.getTransaction().commit();


        }


        list.clear();

       // entityManager.getTransaction().begin();


        entityManager.close();
        entityManager1.close();
        entityManagerFactory.close();


    }


    public  static void show() throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("press 1 for Zoo\npress 2 for Animal");
        int n = Integer.parseInt(bufferedReader.readLine());

        if(n==1){
            Query q = entityManager.createQuery("from Zoo");
            List<Zoo> list = q.getResultList();
            for(Zoo product:list){
                System.out.println(product.getZid()+"  "+product.getZname());
            }
        }else{
            Query q = entityManager.createQuery("from Animal");
            List<Animal> list = q.getResultList();
            for(Animal product:list){
                System.out.println(product.getAid()+"  "+product.getAname());
            }
        }

        entityManager.close();
        entityManagerFactory.close();
    }
    public  static void upadte() throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter 1 for update Zoo name\nEnter 2 for update Animal Name");

        int n = Integer.parseInt(bufferedReader.readLine());

        if(n==1){
            System.out.println("Enter the zoo id you want to update");
            int a = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the new name");
            String name = bufferedReader.readLine();

            Zoo z = entityManager.find(Zoo.class,a);
            z.setZname(name);
            entityManager.getTransaction().begin();
            entityManager.merge(z);
            entityManager.getTransaction().commit();

        }else{
            System.out.println("Enter the Animal id you want to update");
            int a = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the new name");
            String name = bufferedReader.readLine();

            Animal z = entityManager.find(Animal.class,a);
            z.setAname(name);
            entityManager1.getTransaction().begin();
            entityManager1.merge(z);
            entityManager1.getTransaction().commit();
        }

       /* Product p = entityManager.find(Product.class,n);

        System.out.println("Enter the new name");
        String name = bufferedReader.readLine();

        p.setPname(name);*/



        entityManager.close();
        entityManager1.close();
        entityManagerFactory.close();
    }


    public  static void delete() throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter 1 for delete Zoo\nEnter 2 for delete Animal");

        int n = Integer.parseInt(bufferedReader.readLine());
        entityManager.getTransaction().begin();

        if(n==1){
            System.out.println("Enter the id of zoo you want to delete");
            int a = Integer.parseInt(bufferedReader.readLine());

            Zoo z = entityManager.find(Zoo.class,a);
            entityManager.remove(z);
        }else{
            System.out.println("Enter the id of animal you want to delete");
            int a = Integer.parseInt(bufferedReader.readLine());

            Animal z = entityManager.find(Animal.class,a);
            entityManager.remove(z);
        }

        /*Product p = entityManager.find(Product.class,n);


        entityManager.getTransaction().begin();
        entityManager.remove(p);*/
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void Query1(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /*TypedQuery<Animal> query = entityManager.createQuery(
                "SELECT a FROM Animal a WHERE a.age > 4", Animal.class);
        List<Animal> animals = query.getResultList();*/
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
        Root<Animal> root = criteriaQuery.from(Animal.class);
        int age=4;
        criteriaQuery.select(root).where(builder.gt(root.<Number>get("age"), age));
        List<Animal> animals = entityManager.createQuery(criteriaQuery).getResultList();

        for(Animal product:animals){
            System.out.println(product.getAid()+"  "+product.getAname());
        }
    }
    public static void Query2(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /*TypedQuery<Animal> query = entityManager.createQuery(
                "SELECT a FROM Animal a WHERE a.typeOfAnimal = :animalType", Animal.class);
        query.setParameter("animalType", "D");
        List<Animal> animals = query.getResultList();*/

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
        Root<Animal> root = criteriaQuery.from(Animal.class);
        criteriaQuery.select(root).where(builder.equal(root.get("typeOfAnimal"), "D"));
        List<Animal> animals = entityManager.createQuery(criteriaQuery).getResultList();

        for(Animal product:animals){
            System.out.println(product.getAid()+"  "+product.getAname()+""+product.getTypeOfAnimal());
        }

       /* TypedQuery<Animal> query1 = entityManager.createQuery(
                "SELECT a FROM Animal a WHERE a.typeOfAnimal = :animalType", Animal.class);
        query.setParameter("animalType", "W");
        List<Animal> animals1 = query.getResultList();*/
        CriteriaBuilder builder1 = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery1 = builder1.createQuery(Animal.class);
        Root<Animal> root1 = criteriaQuery1.from(Animal.class);
        criteriaQuery.select(root).where(builder.equal(root1.get("typeOfAnimal"), "W"));
        List<Animal> animals1 = entityManager.createQuery(criteriaQuery).getResultList();
        for(Animal product:animals1){
            System.out.println(product.getAid()+"  "+product.getAname()+""+product.getTypeOfAnimal());
        }
    }
    public static void animalNameStartWithD(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> query = cb.createQuery(Animal.class);
        Root<Animal> root = query.from(Animal.class);
        query.select(root)
                .where(cb.like(root.<String>get("aname"),"D%"));

        List<Animal> animals = entityManager.createQuery(query).getResultList();

        for (Animal a :animals) {
            System.out.println(a.getAname()+" "+a.getTypeOfAnimal());

        }
    }
    public static void orderByAge(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Animal> criteriaQuery = criteriaBuilder.createQuery(Animal.class);

        Root<Animal> root = criteriaQuery.from(Animal.class);

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("age")));

        List<Animal> list  = entityManager.createQuery(criteriaQuery).getResultList();


        for (Animal a :list) {
            System.out.println(a.getAname()+" "+a.getTypeOfAnimal()+" "+a.getAge());

        }


    }
}
