package ProyectoFinal.modelo;

    // Profesor es otra clase hija de Persona.
    // Agrega los datos propios del profesor.

public class Profesor extends Persona {
    private String departamento;
    private double salario;

    public Profesor(String nombre, String id, String email,
                    String departamento, double salario) {
        super(nombre, id, email);
        this.departamento = departamento;
        this.salario = salario;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Profesor: " + getNombre()
                + " | ID: " + getId()
                + " | Departamento: " + departamento
                + " | Salario: " + salario);
    }
}
