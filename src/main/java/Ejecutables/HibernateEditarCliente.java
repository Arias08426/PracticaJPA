package Ejecutables;

import com.example.practica_jpa.Cliente;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HibernateEditarCliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a editar:");
        Long id = s.nextLong();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            Cliente cliente = em.find(Cliente.class, id);

            System.out.println("Ingrese el nuevo nombre:");
            String nuevoNombre = s.next();
            cliente.setNombre(nuevoNombre);

            System.out.println("Ingrese el nuevo apellido:");
            String nuevoApellido = s.next();
            cliente.setApellido(nuevoApellido);

            System.out.println("Ingrese la nueva edad:");
            int nuevaFormaPago = s.nextInt();
            cliente.setFormaPago(String.valueOf(nuevaFormaPago));


            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}