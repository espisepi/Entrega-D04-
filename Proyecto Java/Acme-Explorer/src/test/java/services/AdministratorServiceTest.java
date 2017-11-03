// package services;
//
// import javax.transaction.Transactional;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import utilities.AbstractTest;
// import domain.Administrator;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {
// "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
// })
// @Transactional
// public class AdministratorServiceTest extends AbstractTest {
//
// // Service under test ---------------------------------
// @Autowired
// private AdministratorService administratorService;
//
//
// // Tests ----------------------------------------------
//
// @Test
// public void testCreate() {
// this.authenticate("administrator1");
// Administrator administrator;
// administrator = this.administratorService.create();
// administrator.setName("name1");
// administrator.setSurname("surname1");
// administrator.setEmail("admin1@gmail.com");
// administrator.setPhone("7777");
// administrator.setAddress("addres1");
//
// }
//
// @Test
// public void testSave() {
// this.authenticate("administrator1");
// Administrator administrator;
// administrator = this.administratorService.create();
// administrator.setName("name1");
// administrator.setSurname("surname1");
// administrator.setEmail("admin1@gmail.com");
// administrator.setPhone("7777");
// administrator.setAddress("addres1");
// System.out.println(this.administratorService.findAll());
// this.administratorService.save(administrator);
// System.out.println(this.administratorService.findAll());
// }
//}
