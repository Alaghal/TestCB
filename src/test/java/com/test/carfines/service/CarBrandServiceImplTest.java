package com.test.carfines.service;

import com.test.carfines.domain.CarBrand;
import com.test.carfines.repository.CarBrandRepository;
import com.test.carfines.service.impl.CarBrandServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarBrandServiceImplTest {
    @Autowired
    private CarBrandServiceImpl service;

    @MockBean
    private CarBrandRepository repository;

    @Test
    public void addCarBrandTest() {
        CarBrand carBrand = new CarBrand();

        carBrand.setCarBrandName( "SuperBrand" );

        Mockito.doReturn( null )
                .when( repository )
                .findByName( "SuperBrand" );

        boolean isCarBrandCreated = service.addCarBrand( carBrand );

        Assert.assertTrue( isCarBrandCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( carBrand );

    }

    @Test
    public void addCarBrandFailTest() {
        CarBrand carBrand = new CarBrand();

        carBrand.setCarBrandName( "SuperBrand" );

        Mockito.doReturn( new CarBrand() )
                .when( repository )
                .findByName( "SuperBrand" );

        boolean isCarBrandCreated = service.addCarBrand( carBrand );

        Assert.assertFalse( isCarBrandCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );

    }

    @Test
    public void deleteCarBrandTest() {
        Mockito.doReturn( Optional.of (CarBrand.builder().id( 12l ).carBrandName( "Test" ).build())  )
                .when( repository )
                .findById( 12l );

       boolean isDeletedCarBrand = service.delete( 12l );

       Assert.assertTrue( isDeletedCarBrand );

       Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
       Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteCarBrandFailTest() {
        Mockito.doReturn(  null  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedCarBrand = service.delete( 12l );

        Assert.assertFalse( isDeletedCarBrand );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        CarBrand carBrand = new CarBrand();
        carBrand.setCarBrandName( "BrandName" );

        Mockito.doReturn( carBrand )
                .when( repository )
                .findByName( "BrandName" );

        CarBrand expected = service.getByName("BrandName"  );

        Assert.assertEquals(expected, carBrand );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editCarBrandTest(){
        Optional<CarBrand> carBrand = Optional.of( new CarBrand() );
        carBrand.get().setId(1L);
        carBrand.get().setCarBrandName( "SuperBrandName" );

        Mockito.doReturn( carBrand )
                .when( repository )
                .findById(1L);

        boolean isCarBrandEdit = service.editCarBrand( carBrand.get() );

        Assert.assertTrue(isCarBrandEdit);

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void  editCarBrandFailTest(){
        CarBrand carBrand = new CarBrand();
        carBrand.setId(22L );
        carBrand.setCarBrandName( "SuperBrandName" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

         boolean isCarBrandEdit = service.editCarBrand( carBrand );

        Assert.assertFalse(isCarBrandEdit);

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll(){
        List<CarBrand>  carBrandList = new ArrayList<>();
        carBrandList.add( new CarBrand() );
        carBrandList.add( new CarBrand() );

        Mockito.doReturn( carBrandList )
                .when( repository )
                .findAll( );

        var expectedList = service.getAll();

        Assert.assertEquals( expectedList,carBrandList );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll(  );
    }

}
