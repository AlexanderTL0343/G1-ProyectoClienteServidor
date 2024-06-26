/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloController;

import Cliente.ServicioCliente;
import java.util.LinkedList;

/**
 *
 * @author Venta PC
 */
public class Menu {

    Usuario user;
    
    private LinkedList<Vehiculo> inventario = new LinkedList<>();
    private Vehiculo current = null;
    private int currentID = 0;
    private int maxInv;
    
    private LinkedList<Compra> compras = new LinkedList<>();
    private Compra currentC = null;
    private int currentComID;
    private int maxC;
    
    public void actualizarCompras(){
        compras.clear();
        ServicioCliente c = new ServicioCliente("F##"+user.getID());
        String s = c.getRespuesta();
        System.out.println(s);
        String[] x = s.split("##");
        
        for (int i = 0; i < x.length; i++) {
            String[] y = x[i].split("#");
            
            Compra com = new Compra(y[0], y[1], y[2], y[3]);
            compras.add(com);
        }
        
        currentC = compras.getFirst();
        maxC = compras.size();
        currentComID = 0;
        
    }
    public void cambiarCurrentCom(Boolean b){
        if(b&&currentComID!=0){
            currentComID--;
            currentC=compras.get(currentComID);
        }else if(b==false&&currentComID!=maxC-1){
            currentComID++;
            currentC=compras.get(currentComID);
        }
    }
    
    public Usuario getUser(){
        return user;
    }
    
    public String getUserInfoC(){
        return user.getInfo();
    }
    
    public String getVehicleInfoC(){
        String r="Vehiculo eliminado";
        for (Vehiculo vehiculo : inventario) {
            String vid = ""+vehiculo.getId();
            if(vid.equals(currentC.getVe())){
                r=vehiculo.getInfo();
            }
        }
        return r;
    }
    
    public String getEstado(){
        return currentC.getEstado();
    }
    
    public Vehiculo getCurrent(){
        return current;
    }
    
    
    
    public void actualizarInventario(){
        inventario.clear();
        ServicioCliente c = new ServicioCliente("D");
        String s = c.getRespuesta();
        System.out.println(s);
        String[] x = s.split("##");
        
        for (int i = 0; i < x.length; i++) {
            String[] y = x[i].split("#");
            
            Vehiculo v = new Vehiculo(Integer.parseInt(y[0]), y[1], y[2], y[3], y[4], y[5]);
            inventario.add(v);
        }
        
        current = inventario.getFirst();
        maxInv = inventario.size();
        currentID = 0;
    }
    
    public void cambiarCurrentInv(Boolean b){
        if(b&&currentID!=0){
            currentID--;
            current=inventario.get(currentID);
        }else if(b==false&&currentID!=maxInv-1){
            currentID++;
            current=inventario.get(currentID);
        }
    }
    
    public void comprarVehiculo(){
        ServicioCliente c = new ServicioCliente("E##"+user.getID()+"#"+current.getId());
        String s = c.getRespuesta();
    }
    
    

    public boolean initUserLogin(String mensaje) {
        ServicioCliente c = new ServicioCliente("B##" + mensaje);
        String s = c.getRespuesta();
        if (s.equals("false")) {
            return false;
        } else {
            String[] x = s.split("#");
            user = new Usuario(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9]);
            return true;
        }

    }
    
    public void actalizarInfoUsuario(String mensaje){
        ServicioCliente c = new ServicioCliente("C##"+user.getID()+"#"+mensaje);
        System.out.println(c.getRespuesta());
        
    }
    
    public void actuatilzarCurrentUser(int x,String s){
        switch (x) {
            case 1:
                user.setNombre(s);
                break;
            case 2:
                user.setApellidos(s);
                break;
            case 3:
                user.setTelefono(s);
                break;
            default:
                user.setDireccion(s);
                break;
        }
        
        
        
    }
    
    public void registrarUsuario(String mensaje){
        ServicioCliente c = new ServicioCliente("A##"+mensaje);
        String id = c.getRespuesta();
        String[] p = mensaje.split("#");
        System.out.println(id);
        user = new Usuario(id, p[0], p[1], p[2], p[4], p[5], p[6], p[7], p[8], p[9]);
        
    }
}
