import com.example.ProductCategory;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.BackToStockServiceImpl;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.example.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BackToServiceImplTest {
   private BackToStockServiceImpl backToStockService;

    @Before
    public void setUp() {
            Map<Integer, List<User>> products = new HashMap<>();
            int userId = 0;
            List<User> list1 = new ArrayList<>();
            list1.add(new User(++userId,"premium1",true,34));
            list1.add(new User(++userId,"simple1", false,32));
            list1.add(new User(++userId,"middle1",false,78));
            list1.add(new User(++userId,"simple2",false, 56));
            list1.add(new User(++userId,"premium2",true, 58));
            list1.add(new User(++userId,"middle2",false,80));
            list1.add(new User(++userId,"simple3",false, 66));
            list1.add(new User(++userId,"middle3",false, 86));
            products.put(ProductCategory.MEDICAL.ordinal() + 1,list1);

            List<User> list2 = new ArrayList<>();
            list2.add(new User(1,"simple1", false,62));
            list2.add(new User(2,"premium1",true,24));
            list2.add(new User(3,"middle1",false,74));
            list2.add(new User(4,"simple2",false,54));
            products.put(ProductCategory.BOOKS.ordinal() + 1,list2);

            List<User> list3 = new ArrayList<>();
            list3.add(new User(1,"simple1", false,62));
            list3.add(new User(2,"premium1",true,24));
            list3.add(new User(3,"simple2",false,54));
            list3.add(new User(4,"middle1",false,74));
            products.put(ProductCategory.DIGITAL.ordinal() + 1,list3);

            backToStockService = new BackToStockServiceImpl(products);
    }

    @Test
    public void whenSubscribeUserThenReturnListWithNewUser(){
        Product product = new Product(1, ProductCategory.MEDICAL);
        assertThat(backToStockService.subscribedUsers(product).size(),is(8));
        backToStockService.subscribe(new User("Middle4",false,95),product);
        assertThat(backToStockService.subscribedUsers(product).size(),is(9));
    }

    @Test
    public void whenGetListOfSubscribedUsersWithTrueIdOfProduct(){
        Product product1 = new Product(1, ProductCategory.MEDICAL);
        Product product2 = new Product(2, ProductCategory.BOOKS);
        Product product3 = new Product(3, ProductCategory.DIGITAL);
        assertThat(backToStockService.subscribedUsers(product1).size(),is(8));
        assertThat(backToStockService.subscribedUsers(product2).size(),is(4));
        assertThat(backToStockService.subscribedUsers(product3).size(),is(4));
    }

    @Test
    public void whenGetListOfSubscribedUsersWithWrongIdOfProduct(){
        Product product = new Product(5, ProductCategory.MEDICAL);
        try{
            backToStockService.subscribedUsers(product);
        } catch (NotFoundException e){
            assertThat(e.getMessage(),is("Not found list of users"));
        }
    }

    @Test
    public void whenUserIsArgumentOfMethodAndIsNull(){
        User user = null;
        Product product = new Product(1, ProductCategory.MEDICAL);
        try{
            backToStockService.subscribe(user,product);
        } catch (NotFoundException e){
            assertThat(e.getMessage(),is("Not found user"));
        }
    }
    @Test
    public void whenProductIsArgumentOfMethodAndIsNull(){
        User user = new User("user",true,23);
        Product product = null;
        try{
            backToStockService.subscribe(user,product);
        } catch (NotFoundException e){
            assertThat(e.getMessage(),is("Not found product"));
        }
    }

    @Test
    public void whenGetListOfSubscribedUsersWithTrueIdOfProductAndMedicalCategory(){
        Product product = new Product(1, ProductCategory.MEDICAL);
        List<User> list = backToStockService.subscribedUsers(product);
        assertThat(list.get(0).getId(),is(1));
        assertThat(list.get(1).getId(),is(3));
        assertThat(list.get(2).getId(),is(5));
        assertThat(list.get(3).getId(),is(6));
        assertThat(list.get(4).getId(),is(8));
        assertThat(list.get(5).getId(),is(2));
        assertThat(list.get(6).getId(),is(4));
        assertThat(list.get(7).getId(),is(7));
    }

    @Test
    public void whenGetListOfSubscribedUsersWithTrueIdOfProductAndBooksCategory(){
        Product product = new Product(2, ProductCategory.BOOKS);
        List<User> list = backToStockService.subscribedUsers(product);
        assertThat(list.get(0).getId(),is(2));
        assertThat(list.get(1).getId(),is(3));
        assertThat(list.get(2).getId(),is(1));
        assertThat(list.get(3).getId(),is(4));
    }

    @Test
    public void whenGetListOfSubscribedUsersWithTrueIdOfProductAndDigitalCategory(){
        Product product = new Product(3, ProductCategory.DIGITAL);
        List<User> list = backToStockService.subscribedUsers(product);
        assertThat(list.get(0).getId(),is(2));
        assertThat(list.get(1).getId(),is(4));
        assertThat(list.get(2).getId(),is(1));
        assertThat(list.get(3).getId(),is(3));
    }






}
