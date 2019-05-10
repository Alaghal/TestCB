package com.test.carfines.service;


import com.test.carfines.model.TypeFines;
import com.test.carfines.repository.TypeFinesRepository;
import com.test.carfines.service.impl.TypeFinesServiceImpl;
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
public class TypeFinesServiceImplTest {
    @Autowired
    private TypeFinesServiceImpl service;

    @MockBean
    private TypeFinesRepository repository;

    @Test
    public void addFinesTypesTest() {
        TypeFines typeFines = new TypeFines();

        boolean isTypeFinesCreated = service.addTypeFines( typeFines );

        Assert.assertTrue( isTypeFinesCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( typeFines );

    }

    @Test
    public void addTypeFinesFailTest() {
        TypeFines typeFines = new TypeFines();
        typeFines.setNameTypeFines( "1 категория" );

        Mockito.doReturn( new TypeFines() )
                .when( repository )
                .findByName( "1 категория" );

        boolean isTypeFinesCreated = service.addTypeFines( typeFines );

        Assert.assertFalse( isTypeFinesCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );

    }

    @Test
    public void deleteTypeFinesTest() {
        Mockito.doReturn(Optional.of(TypeFines.builder().id( 56l ).nameTypeFines( "TesFynes" ).build()) )
                .when( repository )
                .findById( 56l );

        boolean isDeletedTypeFines = service.delete( 56l );

        Assert.assertTrue( isDeletedTypeFines );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteTypeFinesFailTest() {
        Mockito.doReturn( null )
                .when( repository )
                .findById( 56l );

        boolean isDeletedTypeFines = service.delete( 56l );

        Assert.assertFalse( isDeletedTypeFines );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        TypeFines typeFines = new TypeFines();
        typeFines.setNameTypeFines( "ущерб строению" );

        Mockito.doReturn( typeFines )
                .when( repository )
                .findByName( "ущерб строению" );

        TypeFines expected = service.getByName( "ущерб строению" );

        Assert.assertEquals( expected, typeFines );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editTypeFinesTest() {
        Optional<TypeFines> typeFines = Optional.of( new TypeFines() );
        typeFines.get().setId( 1L );
        typeFines.get().setNameTypeFines( "12" );

        Mockito.doReturn( typeFines )
                .when( repository )
                .findById( 1L );

        boolean isEditOwner = service.editTypeFines( typeFines.get() );

        Assert.assertTrue( isEditOwner );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void editTypeFinesFailTest() {
        TypeFines typeFines = new TypeFines();
        typeFines.setId( 22L );
        typeFines.setNameTypeFines( "5 категория" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isEditTypeFines = service.editTypeFines( typeFines );

        Assert.assertFalse( isEditTypeFines );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll() {
        List<TypeFines> typeFines = new ArrayList<>();
        typeFines.add( new TypeFines() );
        typeFines.add( new TypeFines() );

        Mockito.doReturn( typeFines )
                .when( repository )
                .findAll();

        var expectedList = service.getAll();

        Assert.assertEquals( expectedList, typeFines );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll();

    }

}