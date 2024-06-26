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
        String black="\033[30m"; 
        String red="\033[31m"; 
        String green="\033[32m"; 
        String yellow="\033[33m"; 
        String blue="\033[34m"; 
        String purple="\033[35m"; 
        String cyan="\033[36m"; 
        String white="\033[37m"; 
        String reset="\u001B[0m";

        System.out.print(
              yellow+  "  -----<MENU>----- \n" +reset
                        +"--------------------- \n" +
                        "1. Alta Vuelo\n" +
                        "2. Alta Pasajero\n" +
                        "3. Reserva Vuelo\n" +
                        "4. Modificar reserva\n" +
                        "5. Baja reserva\n" +
                        "6. Salir \n" +
                        "--------------------- \n" +
                       yellow+ "OPCION ELEGIDA: " + reset);

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
                        st.setInt(2, codigov);

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
                        System.out.print("Que id de reserva tienes: ");
                        int idres = sc.nextInt();
                        System.out.println("----------------");
                        st = con.prepareStatement(
                                "SELECT id_reserva,id_vuelo,id_pasajero,n_asiento FROM reservaVuelos.Vuelos_Pasajeros where id_reserva = '"
                                        + idres + "'");
                        ResultSet ps = st.executeQuery();

                        while (ps.next()) {
                            int codigo = ps.getInt("id_reserva");
                            int origen = ps.getInt("id_vuelo");
                            int destino = ps.getInt("id_pasajero");
                            int fecha = ps.getInt("n_asiento");
                            System.out.println("ID RESERVA: " + codigo + "\n" +
                                    "ID DE VUELO: " + origen + "\n" +
                                    "ID DE PASAJERO: " + destino + "\n" +
                                    "NUMERO DE ASIENTO: " + fecha + "\n");
                        }

                        ps.close();
                        st.close();
                        System.out.println("Por que asiento desea cambiarlo: ");
                        int asiento = sc.nextInt();
                        st = con.prepareStatement(
                                "UPDATE reservaVuelos.Vuelos_Pasajeros SET n_asiento= ? where id_reserva= ?");
                        st.setInt(1, asiento);
                        st.setInt(2, idres);
                        int r = st.executeUpdate();
                        if (r > 0) {
                            System.out.println("Se ha cambiado con exito !!!");

                        } else {
                            System.out.println("No se ha podido cambiar, ese asiento esta Ocupado!!!");
                        }

                    } catch (Exception e) {
                        e.getMessage();
                    }

                    break;
                case 5:
                    System.out.print("Que id de reserva tienes: ");
                    int idres = sc.nextInt();
                    sc.nextLine();
                    System.out.println("----------------");
                    st = con.prepareStatement(
                            "SELECT id_reserva,id_vuelo,id_pasajero,n_asiento FROM reservaVuelos.Vuelos_Pasajeros where id_reserva = '"
                                    + idres + "'");
                    ResultSet ps = st.executeQuery();

                    while (ps.next()) {
                        int codigo = ps.getInt("id_reserva");
                        int origen = ps.getInt("id_vuelo");
                        int destino = ps.getInt("id_pasajero");
                        int fecha = ps.getInt("n_asiento");
                        System.out.println("ID RESERVA: " + codigo + "\n" +
                                "ID DE VUELO: " + origen + "\n" +
                                "ID DE PASAJERO: " + destino + "\n" +
                                "NUMERO DE ASIENTO: " + fecha + "\n");
                    }

                    ps.close();
                    st.close();
                    System.out.print("Deseas"+red+" ELIMINAR"+ reset+" la reserva ?  (Si/No): ");
                    String eliminar = sc.nextLine();
                    if (eliminar.equals("Si")) {
                        st = con.prepareStatement(
                                "DELETE FROM reservaVuelos.Vuelos_Pasajeros where id_reserva = ?");
                        st.setInt(1, idres);
                        int r = st.executeUpdate();
                        System.out.println("--------------------");
                        System.out.println(red+"Tu reserva a sido ELIMINADA" + reset);
                        System.out.println("/n");
                    }

                    break;

            }
            System.out.print(
                yellow+  "  -----<MENU>----- \n " +reset
                          + "--------------------- \n" +
                          "1. Alta Vuelo\n" +
                          "2. Alta Pasajero\n" +
                          "3. Reserva Vuelo\n" +
                          "4. Modificar reserva\n" +
                          "5. Baja reserva\n" +
                          "6. Salir \n" +
                          "--------------------- \n" +
                         yellow+ "OPCION ELEGIDA: " + reset);
  
            System.out.println();
            n = sc.nextInt();
            sc.nextLine();
            System.out.println();
        }
    }

}

