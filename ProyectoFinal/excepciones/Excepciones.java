package excepciones;

    // Todas las excepciones personalizadas se guardan en este archivo.
    // Cada una permite informar un tipo de error especifico.

public class Excepciones {
    public static class PreRequisitoNoAprobadoException extends Exception {
        public PreRequisitoNoAprobadoException(String mensaje) { super(mensaje); }
    }

    public static class HorarioConflictivoException extends Exception {
        public HorarioConflictivoException(String mensaje) { super(mensaje); }
    }

    public static class CupoLlenoException extends Exception {
        public CupoLlenoException(String mensaje) { super(mensaje); }
    }

    public static class EstudianteNoEncontradoException extends Exception {
        public EstudianteNoEncontradoException(String mensaje) { super(mensaje); }
    }

    public static class ColaDeEsperaVaciaException extends Exception {
        public ColaDeEsperaVaciaException(String mensaje) { super(mensaje); }
    }

    public static class PilaDeshacerVaciaException extends Exception {
        public PilaDeshacerVaciaException(String mensaje) { super(mensaje); }
    }

    public static class ArchivoInvalidoException extends Exception {
        public ArchivoInvalidoException(String mensaje) { super(mensaje); }
    }
}
