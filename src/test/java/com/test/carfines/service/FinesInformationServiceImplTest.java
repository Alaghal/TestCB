package com.test.carfines.service;


import com.test.carfines.domain.FinesInformation;
import com.test.carfines.repository.FinesInformationRepository;
import com.test.carfines.service.impl.FinesInformationServiceImpl;
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
public class FinesInformationServiceImplTest {
    @Autowired
    private FinesInformationServiceImpl service;

    @MockBean
    private FinesInformationRepository repository;

    @Test
    public void addFinesInformationTest() {
        FinesInformation finesInformation = new FinesInformation();
        finesInformation.setId( 112l );

        Mockito.doReturn( finesInformation )
                .when( repository )
                .saveAndFlush( finesInformation );

        FinesInformation expectedFinesInformation = service.addFinesInformation( finesInformation );

        Assert.assertEquals( expectedFinesInformation, finesInformation );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );

    }

    @Test
    public void addFinesInformationFailTest() {
        FinesInformation finesInformation = new FinesInformation();
        finesInformation.setId( 223l );

        Mockito.doReturn( Optional.of( new FinesInformation() ) )
                .when( repository )
                .findById( finesInformation.getId() );

        FinesInformation expectedFinesInformation = service.addFinesInformation( finesInformation );

        Assert.assertNotEquals( expectedFinesInformation, finesInformation );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );

    }

    @Test
    public void deleteFinesInformationTest() {
        Mockito.doReturn(Optional.of(FinesInformation.builder().id( 65l ).build()) )
                .when( repository )
                .findById( 65l );

        boolean isDeletedFinesInformation = service.delete( 65l );

        Assert.assertTrue( isDeletedFinesInformation );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteFinesInformationFailTest() {
        Mockito.doReturn(null )
                .when( repository )
                .findById( 65l );

        boolean isDeletedFinesInformation = service.delete( 65l );

        Assert.assertFalse( isDeletedFinesInformation );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }



    @Test
    public void getByIdTest() {
        FinesInformation finesInformation = new FinesInformation();
        finesInformation.setId( 12l );

        Mockito.doReturn( Optional.of( finesInformation ) )
                .when( repository )
                .findById( 12l );

        FinesInformation expected = service.getFinesInformationById( 12l );

        Assert.assertEquals( expected, finesInformation );

        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editFinesInformationTest() {
        FinesInformation finesInformation = new FinesInformation();
        finesInformation.setId( 1L );


        Mockito.doReturn( Optional.of( finesInformation ) )
                .when( repository )
                .findById( 1L );

        boolean isEditFinesInformation = service.editFinesInformation( finesInformation );

        Assert.assertTrue( isEditFinesInformation );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editFinesInformationFailTest() {
        FinesInformation finesInformation = new FinesInformation();
        finesInformation.setId( 22L );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isEditFinesInformation = service.editFinesInformation( finesInformation );

        Assert.assertFalse( isEditFinesInformation );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAllTest() {
        List<FinesInformation> finesInformations = new ArrayList<>();
        finesInformations.add( new FinesInformation() );
        finesInformations.add( new FinesInformation() );

        Mockito.doReturn( finesInformations )
                .when( repository )
                .findAll();

        var expectedList = service.getAll();

        Assert.assertEquals( expectedList, finesInformations );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll();

    }

}