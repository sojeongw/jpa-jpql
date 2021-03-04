package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setUsername("member");
      em.persist(member);

      TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
      TypedQuery<String> query2 = em
          .createQuery("select m.username, m.age from Member m", String.class);
      Query query3 = em.createQuery("select m.username, m.age from Member m");

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    entityManagerFactory.close();
  }
}
