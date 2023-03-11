package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import com.example.demo.entity.SanPham;
import com.example.demo.response.SanPhamRespone;
import com.example.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thang
 */
public class SanPhamRepository {

    public List<SanPhamRespone> getAll() {
        List<SanPhamRespone> sanPhams = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new com.example.demo.response.SanPhamRespone(sp.id, sp.ten, sp.gia) FROM SanPham sp", SanPhamRespone.class);
            sanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sanPhams;
    }

    public boolean add(SanPham sanPham){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            session.persist(sanPham);
            // cách 2
            // session.save(sanPham);
            // cách 3
            // session.saveOrUpdate(sanPham);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean update(SanPham sanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(sanPham);
            // cách 2
            // session.saveOrUpdate(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public boolean delete(SanPham sanPham){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            session.delete(sanPham);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public SanPham getOne(int id){
        SanPham sanPham = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            sanPham = session.get(SanPham.class, id);
            transaction.commit();
        } catch (Exception e) {
        }
        return sanPham;
    }

    public SanPhamRespone findById(int id){
        SanPhamRespone sanPham = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT new com.example.demo.response.SanPhamRespone(sp.id, sp.ten, sp.gia) FROM SanPham sp WHERE sp.id = :id", SanPhamRespone.class);
            query.setParameter("id", id);
            sanPham = (SanPhamRespone) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        }
        return sanPham;
    }
}
