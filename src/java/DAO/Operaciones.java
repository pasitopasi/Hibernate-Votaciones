/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pasito
 */
public class Operaciones {

    /**
     *
     */
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public boolean IntroducirUsuario(SessionFactory sessionbuilder, Votante usuario) {
        Session session = sessionbuilder.openSession();
        Transaction Tx = null;
        boolean salida = true;
        try {
            Tx = session.beginTransaction();
            session.save(usuario);
            Tx.commit();
        } catch (HibernateException HE) {
            if (Tx != null) {
                Tx.rollback();
                salida = false;
            }
        } finally {
            session.close();
        }
        return salida;
    }

    private String decodificacionUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    public String AccesoSistema(SessionFactory sessionbuilder, Votante usuario) {
        String pass = "";
        Session session = sessionbuilder.openSession();
        Query sql = session.createQuery("FROM Votante WHERE DNI= :dnit ");
        sql.setParameter("dnit", usuario.getDni());

        String s = decodificacionUTF8(usuario.getContrasena());

        List<Votante> listas_Votantes = sql.list();
        if (!listas_Votantes.isEmpty()) {
            Votante v = (Votante) listas_Votantes.get(0);
            String voto = v.getVoto();
            if ((decodificacionUTF8(v.getContrasena()).equals(s))) {
                if (voto.equals("si")) {
                    session.close();
                    return "ya ha votado.";
                } else {
                    session.close();
                    return "si";
                }
            }
        }
        session.close();
        return "el usuario no esta registrado o te has equivocado de contrasena.";
    }

    public ArrayList<Reloj> ObtenerRelojes(SessionFactory sessionbuilder) {
        ArrayList<Reloj> relojes = new ArrayList<>();

        Session session = sessionbuilder.openSession();
        Query sql = session.createQuery("FROM Reloj");

        List lista_relojes = sql.list();

        if (!lista_relojes.isEmpty()) {
            Iterator<Reloj> it = lista_relojes.iterator();
            while (it.hasNext()) {
                Reloj r = it.next();
                relojes.add(r);
            }
        }
        session.close();
        return relojes;
    }

    public void votarReloj(SessionFactory sessionbuilder, Votante usuario, Reloj reloj) {
        /* registro el voto */
        votar(sessionbuilder, usuario);
        /* cuento el voto */
        sumarVoto(sessionbuilder, reloj);
    }

    private boolean votar(SessionFactory sessionbuilder, Votante usuario) {
        boolean salida = true;
        Session session = sessionbuilder.openSession();
        Query q = session.createQuery("FROM Votante WHERE DNI= :_vdni");
        q.setParameter("_vdni", usuario.getDni());
        List List_Votante = q.list();
        if (!List_Votante.isEmpty()) {
            usuario.setId(((Votante) List_Votante.get(0)).getId());
            usuario.setNombre(((Votante) List_Votante.get(0)).getNombre());
            usuario.setApellido(((Votante) List_Votante.get(0)).getApellido());
            usuario.setContrasena(((Votante) List_Votante.get(0)).getContrasena());
            usuario.setVoto("si");
        }
        Transaction Tx = null;
        try {
            Tx = session.beginTransaction();
            session.update(session.merge(usuario));
            Tx.commit();
        } catch (HibernateException HE) {
            if (Tx != null) {
                Tx.rollback();
                salida = false;
            }
        } finally {
            session.close();
        }
        return salida;
    }

    private boolean sumarVoto(SessionFactory sessionbuilder, Reloj reloj) {
        Session session = sessionbuilder.openSession();
        boolean salida = true;
        Query q = session.createQuery("FROM Reloj WHERE ID= :_idR");
        q.setParameter("_idR", reloj.getId());
        List List_Reloj = q.list();

        if (!List_Reloj.isEmpty()) {
            reloj.setMarca(((Reloj) (List_Reloj.get(0))).getMarca());
            reloj.setIcono(((Reloj) (List_Reloj.get(0))).getIcono());
            reloj.setVotos((((Reloj) (List_Reloj.get(0))).getVotos()) + 1);
        }
        Transaction Tx = null;
        try {
            Tx = session.beginTransaction();
            session.update(session.merge(reloj));
            Tx.commit();
        } catch (HibernateException HE) {
            if (Tx != null) {
                Tx.rollback();
                salida = false;
            }
        } finally {
            session.close();
        }
        return salida;
    }

    public double escrutinio(SessionFactory sessionbuilder) {
        double x = Votantes(sessionbuilder);
        double y = VotantesSI(sessionbuilder);
        return y / x * 100;
    }

    private double Votantes(SessionFactory sessionbuilder) {
        Session session = sessionbuilder.openSession();
        Query sql = session.createQuery("FROM Votante");

        List lista_Votantes = sql.list();
        session.close();
        return lista_Votantes.size();
    }

    private double VotantesSI(SessionFactory sessionbuilder) {
        Session session = sessionbuilder.openSession();
        Query sql = session.createQuery("FROM Votante WHERE Voto='si'");

        List lista_Votantes = sql.list();
        session.close();
        return lista_Votantes.size();
    }

    public ArrayList<Votante> ObtenerVotantes(SessionFactory sessionbuilder) {
        Session session = sessionbuilder.openSession();
        return (ArrayList<Votante>) session.createQuery("FROM Votante").list();
    }

    public boolean BorrarUsuario(SessionFactory sessionbuilder, Votante usuario) {
        Session session = sessionbuilder.openSession();
        boolean salida = true;
        Query q = session.createQuery("FROM Votante WHERE DNI= :_dni");
        q.setParameter("_dni", usuario.getDni());
        List list_Votante = q.list();
        Votante votante = new Votante();
        if (!list_Votante.isEmpty()) {
            votante = (Votante) list_Votante.get(0);
        }
        Transaction Tx = null;
        try {
            Tx = session.beginTransaction();
            session.delete(votante);
            Tx.commit();
        } catch (HibernateException HE) {
            if (Tx != null) {
                Tx.rollback();
                salida = false;
            }
        } finally {
            session.close();
        }
        return salida;
    }

    public Reloj obtenerReloj(SessionFactory sessionbuilder, Reloj reloj) {
        Session session = sessionbuilder.openSession();
        Query sql = session.createQuery("FROM Reloj where ID=:Rid");
        sql.setParameter("Rid", reloj.getId());
        List<Reloj> relojL = sql.list();
        session.close();
        return relojL.get(0);
    }
    
    public String cambioContras(SessionFactory sessionbuilder, Votante usuario){
        String salida = "";
        Session session = sessionbuilder.openSession();
        Query q = session.createQuery("FROM Votante WHERE DNI= :_vdni");
        q.setParameter("_vdni", usuario.getDni());
        List List_Votante = q.list();
        if (!List_Votante.isEmpty()) {
            usuario.setId(((Votante) List_Votante.get(0)).getId());
            usuario.setNombre(((Votante) List_Votante.get(0)).getNombre());
            usuario.setApellido(((Votante) List_Votante.get(0)).getApellido());
            usuario.setVoto(((Votante) List_Votante.get(0)).getVoto());
        }
        Transaction Tx = null;
        try {
            Tx = session.beginTransaction();
            session.update(session.merge(usuario));
            Tx.commit();
            salida="si";
        } catch (HibernateException HE) {
            if (Tx != null) {
                Tx.rollback();
                salida = HE.getMessage();
            }
        } finally {
            session.close();
        }
        return salida;
    }
}
