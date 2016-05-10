package com.xxplus.batch;

import com.xxplus.base.SpringBaseTest;
import com.xxplus.entity.CompanyEntity;
import com.xxplus.services.CompanyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

/**
 * Created by admin on 16/05/08.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BatchInsertDataTest extends SpringBaseTest{

    @Autowired
    private CompanyService companyService;

    @Test
    public void testInsert(){

//        Collection<CompanyEntity> coll1 = newCompanys(0, 300);
//        Collection<CompanyEntity> coll2 = newCompanys(300, 600);
//        Collection<CompanyEntity> coll3 = newCompanys(600, 900);
//        Collection<CompanyEntity> coll4 = newCompanys(900, 1000);

        //1821ms
        companyService.persist(newCompanys(0, 1000));

//        new Thread(new BatchInsertThread(coll1)).run();
//        new Thread(new BatchInsertThread(coll2)).run();
//        new Thread(new BatchInsertThread(coll3)).run();
//        new Thread(new BatchInsertThread(coll4)).run();
    }



    class BatchInsertThread implements Runnable{

        private Collection<CompanyEntity> coll;

        public BatchInsertThread(Collection<CompanyEntity> coll){
            this.coll = coll;
        }

        @Override
        public void run() {
            batch(coll);
        }
    }

    private void batch(Collection<CompanyEntity> coll){
        companyService.persist(coll);
    }


    private Collection<CompanyEntity> newCompanys(int offset, int limit){

        Collection<CompanyEntity> list = new ArrayList<>();
        for(int i = offset; i < limit; i++){
            list.add(buildCompany(i));
        }

        return list;
    }


    private CompanyEntity buildCompany(int id){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyNo("COMPANY-" + id);
        companyEntity.setFullName("COMPANY-" + id);
        companyEntity.setLogo("COMPANY-LOGO-");
        companyEntity.setName("COMPANY NAME");
        return companyEntity;
    }
}
