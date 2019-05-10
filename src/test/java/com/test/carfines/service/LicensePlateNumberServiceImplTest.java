package com.test.carfines.service;


import com.test.carfines.model.FinesInformation;
import com.test.carfines.model.LicensePlateNumber;
import com.test.carfines.repository.LicensePlateNumberRepository;
import com.test.carfines.service.impl.LicensePlateNumberServiceImpl;
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
public class LicensePlateNumberServiceImplTest {
    @Autowired
    private LicensePlateNumberServiceImpl service;

    @MockBean
    private LicensePlateNumberRepository repository;

    @Test
    public void addLPNTest() {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber();
        licensePlateNumber.setId( 112l );

        Mockito.doReturn( licensePlateNumber )
                .when( repository )
                .saveAndFlush( licensePlateNumber );

        LicensePlateNumber expectedLicensePlateNumber = service.addLicensePlateNumber( licensePlateNumber );

        Assert.assertEquals( expectedLicensePlateNumber, licensePlateNumber );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );

    }

    @Test
    public void addLPNFailTest() {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber();
        licensePlateNumber.setId( 223l );

        Mockito.doReturn(Optional.of( new LicensePlateNumber()) )
                .when( repository )
                .findById( licensePlateNumber.getId() );

        LicensePlateNumber expectedFinesInformation = service.addLicensePlateNumber( licensePlateNumber );

        Assert.assertNotEquals( expectedFinesInformation, licensePlateNumber );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );

    }

    @Test
    public void deleteLPNTest() {
        Mockito.doReturn(Optional.of(LicensePlateNumber.builder().id( 98l ).build()) )
                .when( repository )
                .findById( 98l );

        boolean isDeletedFinesInformation = service.delete( 98l );

        Assert.assertTrue( isDeletedFinesInformation );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteLPNFailTest() {
        Mockito.doReturn( null)
                .when( repository )
                .findById( 98l );

        boolean isDeletedFinesInformation = service.delete( 98l );

        Assert.assertFalse( isDeletedFinesInformation );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByIdTest() {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber();
        licensePlateNumber.setId( 12l );

        Mockito.doReturn( Optional.of( licensePlateNumber) )
                .when( repository )
                .findById( 12l );

        LicensePlateNumber expected = service.getLicensePlateNumberById( 12l );

        Assert.assertEquals( expected, licensePlateNumber );

        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editLPNTest() {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber();
        licensePlateNumber.setId( 1L );


        Mockito.doReturn( Optional.of( licensePlateNumber ) )
                .when( repository )
                .findById( 1L );

        boolean isEditLicensePlateNumber = service.editLicensePlateNumber( licensePlateNumber );

        Assert.assertTrue( isEditLicensePlateNumber );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editFinesInformationFailTest() {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber();
        licensePlateNumber.setId( 22L );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isEditLicensePlateNumber = service.editLicensePlateNumber( licensePlateNumber );

        Assert.assertFalse( isEditLicensePlateNumber );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll() {
        List<LicensePlateNumber> licensePlateNumbers = new ArrayList<>();
        licensePlateNumbers.add( new LicensePlateNumber() );
        licensePlateNumbers.add( new LicensePlateNumber() );

        Mockito.doReturn( licensePlateNumbers )
                .when( repository )
                .findAll();

        var expectedList = service.getAll();

        Assert.assertEquals( expectedList, licensePlateNumbers );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll();

    }
}