import it.si2001.springMVC.model.Typology;
import it.si2001.springMVC.service.TypologyService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestDatabase {

    @Test
    public void test(){


        TypologyService service2 = new TypologyService();
        Typology t = new Typology();
        t.setType("Admin");
        service2.save(t);

        assertNotNull(t);
    }



}
