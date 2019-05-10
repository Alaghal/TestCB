package com.test.carfines.service;


import com.test.carfines.model.Owner;
import com.test.carfines.repository.OwnerRepository;
import com.test.carfines.service.impl.OwnerServiceImpl;
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
public class OwnerServiceImplTest {
    @Autowired
    private OwnerServiceImpl service;

    @MockBean
    private OwnerRepository repository;

    @Test
    public void addOwnerTest() {
        Owner owner = new Owner();
        owner.setNameOwner( "Тимур" );

        Mockito.doReturn( null )
                .when( repository )
                .findByName( "Тимур" );

        boolean isOwnerCreated = service.addOwner( owner );

        Assert.assertTrue( isOwnerCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( owner );

    }

    @Test
    public void addOwnerFailTest() {
        Owner owner = new Owner();
        owner.setNameOwner( "Леха" );

        Mockito.doReturn( new Owner() )
                .when( repository )
                .findByName( "Леха" );

        boolean isOwnerCreated = service.addOwner( owner );

        Assert.assertFalse( isOwnerCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );

    }

    @Test
    public void deleteOwnerTest() {
        Mockito.doReturn(Optional.of( Owner.builder().id( 98l ).nameOwner( "TestBane" ).build()) )
                .when( repository )
                .findById( 98l );

        boolean isDeletedOwner = service.delete( 98l );

        Assert.assertTrue( isDeletedOwner );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteOwnerFailTest() {
        Mockito.doReturn( null )
                .when( repository )
                .findById( 122l );

        boolean isDeletedOwner = service.delete( 122l );

        Assert.assertFalse( isDeletedOwner );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        Owner owner = new Owner();
        owner.setNameOwner( "Макс" );

        Mockito.doReturn( owner )
                .when( repository )
                .findByName( "Макс" );

        Owner expected = service.getByName( "Макс" );

        Assert.assertEquals( expected, owner );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editOwnerTest() {
        Owner owner =  new Owner();
        owner.setId( 1L );
        owner.setNameOwner( "Антон" );

        Mockito.doReturn( Optional.of(owner) )
                .when( repository )
                .findById( 1L );

        boolean isEditOwner = service.editOwner( owner );

        Assert.assertTrue( isEditOwner );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editOwnerFailTest() {
        Owner owner = new Owner();
        owner.setId( 22L );
        owner.setNameOwner( "Витя" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isEditOwner = service.editOwner( owner );

        Assert.assertFalse( isEditOwner );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll() {
        List<Owner> owners = new ArrayList<>();
        owners.add( new Owner() );
        owners.add( new Owner() );

        Mockito.doReturn( owners )
                .when( repository )
                .findAll();

        var expectedList = service.getAll();

        Assert.assertEquals( expectedList, owners );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll();

    }

}