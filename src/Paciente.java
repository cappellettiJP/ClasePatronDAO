

public class Paciente {
    private Long id;
    private String nombre;
    private int edad;
    private String password;

    public Paciente(String nombre, int edad, String password) {
        this.nombre = nombre;
        this.edad = edad;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
