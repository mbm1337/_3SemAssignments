package org.example.week03.GLS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageDAOTest {
    private static PackageDAO packageDAO;
    private static EntityManager entityManager;

    @BeforeAll
    static void setUp() {
        EntityManagerFactory emf = HibernateConfig.buildEntityFactoryConfig();
        entityManager = emf.createEntityManager();
        packageDAO = new PackageDAO();
        Query query = entityManager.createNativeQuery("DELETE FROM packages");
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();

    }

    @AfterAll
    static void tearDown() {
        entityManager.close();
    }

    @Test
    public void testPersistPackage() {
    Package aPackage = Package.builder()
            .trackingNumber("123456")
            .senderName("John Doe")
            .receiverName("Jane Doe")
            .deliverStatus(Package.DeliverStatus.PENDING)
            .build();
    packageDAO.save(aPackage);

    Package foundPackage = packageDAO.findByTrackingNumber("123456");
    assertNotNull(foundPackage);
    assertEquals("123456", foundPackage.getTrackingNumber());
    assertEquals("John Doe", foundPackage.getSenderName());
    assertEquals("Jane Doe", foundPackage.getReceiverName());
    assertEquals(Package.DeliverStatus.PENDING, foundPackage.getDeliverStatus());

    packageDAO.delete("123456");

    foundPackage = packageDAO.findByTrackingNumber("123456");
    assertNull(foundPackage);


    }
}
