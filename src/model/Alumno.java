package model;

//This class serves to instantiate "Alumno" with personal data
public class Alumno {
    
    private String registro;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    
    //Contructor without initialize data
    public Alumno() {
        
    }

    //Contructor without initialize data
    public Alumno(String registro, String dni, String nombre, String apellido1, String apellido2) {
        this.registro = registro;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    //Getter and Setters
    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    
    
}