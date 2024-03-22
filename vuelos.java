import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class vuelos {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        PreparedStatement st = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/reservaVuelos", "root", "root");

        System.out.print(
                "  -----<MENU>----- \n"
                        + "--------------------- \n" +
                        "1. Alta Vuelo\n" +
                        "2. Alta Pasajero\n" +
                        "3. Reserva Vuelo\n" +
                        "4. Modificar reserva\n" +
                        "5. Baja reserva\n" +
                        "6. Salir \n" +
                        "--------------------- \n" +
                        "OPCION ELEGIDA: ");
                      
        int n;
        n = sc.nextInt();
        sc.nextLine();
        System.out.println();
        while (n != 6) {

            switch (n) {
                case 1:
                    try {
                        st = con.prepareStatement(
                                "INSERT INTO ReservaVuelos.Vuelos (origen, destino, fecha, capacidad) values (?,?,?,?)");

                        System.out.println("Origen del vuelo: ");
                        String origen = sc.nextLine();
                        st.setString(1, origen);

                        System.out.println("Destino del vuelo: ");
                        String destino = sc.nextLine();
                        st.setString(2, destino);

                        System.out.println("Fecha del vuelo en el formato AAAA-MM-DD: ");
                        String fecha = sc.nextLine();
                        st.setString(3, fecha);

                        System.out.println("Capacidad del vuelo: ");
                        int cap = sc.nextInt();
                        st.setInt(4, cap);

                        int r = st.executeUpdate();
                        break;
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    break;
                case 2:
                    try {
                        st = con.prepareStatement(
                                "INSERT INTO Vuelos_Pasajeros (id_pasajeros, id_vuelos, n_asiento) VALUES (?, ?, ?)");

                        System.out.println("Cual es tu Id de Pasajero: ");
                        int idpas = sc.nextInt();
                        st.setInt(1, idpas);

                        System.out.println("Cual es el ID de Vuelo: ");
                        int codigov = sc.nextInt();
                        st.setInt(2, codigov);

                        System.out.println("Que asiento quieres reservar: ");
                        int asiento = sc.nextInt();
                        st.setInt(3, asiento);

                        int rs = st.executeUpdate();
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    break;
                case 3:
                    try {

                        st = con.prepareStatement("SELECT id_vuelo,origen,destino,fecha,capacidad FROM Vuelos");
                        ResultSet ps = st.executeQuery();

                        System.out.println("Estos son los vuelos disponibles:\n--------------------------------");
                        while (ps.next()) {
                            int codigo = ps.getInt("id_vuelo");
                            String origen = ps.getString("origen");
                            String destino = ps.getString("destino");
                            String fecha = ps.getString("fecha");
                            int capacidad = ps.getInt("capacidad");
                            System.out.println("ID VUELO: " + codigo + "\n" +
                                    "Origen del vuelo: " + origen + "\n" +
                                    "Destino del vuelo: " + destino + "\n" +
                                    "Fecha del vuelo: " + fecha + "\n" +
                                    "Capacidad del avión: " + capacidad + "\n");
                        }

                        ps.close();
                        st.close();

                        st = con.prepareStatement(
                                "INSERT INTO Vuelos_Pasajeros (id_pasajero, id_vuelo, n_asiento) VALUES (?, ?, ?)");

                        System.out.println("¿Cuál es tu ID de Pasajero?");
                        int idpas = sc.nextInt();
                        st.setInt(1, idpas);

                        System.out.println("¿Cuál es el ID de Vuelo que quieres reservar?");
                        int codigov = sc.nextInt();
                        st.setInt(2, codigov); System.out.println();

                        System.out.println("¿Qué asiento quieres reservar?");
                        int asiento = sc.nextInt();
                        st.setInt(3, asiento);

                        int r = st.executeUpdate();
                       
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:
                try {
                    
                } catch (Exception e) {
                    e.getMessage(); //mec 2
                }

                    break;
                case 5:

                    break;

            }
            System.out.print(
                    "  -----<MENU>----- \n"
                            + "--------------------- \n" +
                            "1. Alta Vuelo\n" +
                            "2. Alta Pasajero\n" +
                            "3. Reserva Vuelo\n" +
                            "4. Modificar reserva\n" +
                            "5. Baja reserva\n" +
                            "6. Salir \n" +
                            "--------------------- \n" +
                            "OPCION ELEGIDA: ");
                            System.out.println();
            n = sc.nextInt();
            sc.nextLine();
            System.out.println();
        }
    }

}