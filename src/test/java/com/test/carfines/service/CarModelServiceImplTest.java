package com.test.carfines.service;

import com.test.carfines.model.CarModel;
import com.test.carfines.repository.CarModelRepository;
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
public class CarModelServiceImplTest {
    @Autowired
    private CarModelService service;

    @MockBean
    private CarModelRepository repository;

    @Test
    public void addCarBrandTest() {
        CarModel carModel = new CarModel();

        boolean isCarModelCreated = service.addCarModel( carModel );

        Assert.assertTrue( isCarModelCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( carModel );

    }

    @Test
    public void addCarBrandFailTest() {
        CarModel carModel = new CarModel();
        carModel.setCarModelName( "SuperModel" );

        Mockito.doReturn( new CarModel() )
                .when( repository )
                .findByName( "SuperModel" );

        boolean isCarModelCreated = service.addCarModel( carModel );

        Assert.assertFalse( isCarModelCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );

    }

    @Test
    public void deleteCarModelTest() {
        Mockito.doReturn( Optional.of( CarModel.builder().id( 12l ).carModelName( "Test" ).build()) )
                .when( repository )
                .findById( 12l );

        boolean isDeletedCarModel = service.delete( 12l );

        Assert.assertTrue( isDeletedCarModel );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteCarModelFailTest() {
        Mockito.doReturn( null )
                .when( repository )
                .findById( 25l );

        boolean isDeletedCarModel = service.delete( 25l );

        Assert.assertFalse( isDeletedCarModel );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }


    @Test
    public void getByNameTest() {
        CarModel carModel = new CarModel();
        carModel.setCarModelName( "StarModel" );

        Mockito.doReturn( carModel )
                .when( repository )
                .findByName( "StarModel" );

        CarModel expected = service.getByName( "StarModel" );

        Assert.assertEquals( expected, carModel );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editCarModelTest() {
        Optional<CarModel> carModel = Optional.of( new CarModel() );
        carModel.get().setId( 1L );
        carModel.get().setCarModelName( "StarName" );

        Mockito.doReturn( carModel )
                .when( repository )
                .findById( 1L );

        boolean isEditCarModel = service.editCarModel( carModel.get() );

        Assert.assertTrue( isEditCarModel );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editCarMpdelFailTest() {
        CarModel carModel = new CarModel();
        carModel.setId( 22L );
        carModel.setCarModelName( "Paravoz" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isEditCarModel = service.editCarModel( carModel );

        Assert.assertFalse( isEditCarModel );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll() {
        List<CarModel> carModelList = new ArrayList<>();
        carModelList.add( new CarModel() );
        carModelList.add( new CarModel() );

        Mockito.doReturn( carModelList )
                .when( repository )
                .findAll();

        var expectedList = service.getAll();

        Assert.assertEquals( expectedList, carModelList );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll();

    }

}