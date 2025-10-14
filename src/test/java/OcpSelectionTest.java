import com.example.metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.presentation.Presentation2;

import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {

    @Test
    public void devProfile_choisitDao2_300() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");     // DaoImpl2 (150)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(300.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void prodProfile_choisitDao_200() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");    // DaoImpl (100)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(200.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void fileProfile_choisitDaoFile_360() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("file");    // DaoFile (180)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(360.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void apiProfile_choisitDaoApi_440() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("api");     // DaoApi (220)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(440.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void propertyDrivenConfig_choisitDaoApi_440() {
        // Test de la variante C avec sélection par propriété
        System.setProperty("dao.target", "daoApi");
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // Pas de profil actif pour que tous les beans soient disponibles
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(440.0, m.calcul(), 1e-6);
        ctx.close();
        
        // Nettoyer la propriété système
        System.clearProperty("dao.target");
    }

    @Test
    public void propertyDrivenConfig_choisitDaoFile_360() {
        // Test de la variante C avec sélection par propriété
        System.setProperty("dao.target", "daoFile");
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // Pas de profil actif pour que tous les beans soient disponibles
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(360.0, m.calcul(), 1e-6);
        ctx.close();
        
        // Nettoyer la propriété système
        System.clearProperty("dao.target");
    }
}
