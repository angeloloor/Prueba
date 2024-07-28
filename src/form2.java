import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form2 extends JFrame {
    private JPanel panel2;
    private JTextField cedulatxt;
    private JTextField nombretxt;
    private JButton ingresardatos;
    private JButton Productobutton;
    private JButton irabuscar;

    public form2() {
        setTitle("Insertar Cliente");
        setContentPane(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setPreferredSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(400, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        ingresardatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/floricola";
                String user = "root";
                String password = "123456";

                // Validar campos
                if ( nombretxt.getText().equals("") || cedulatxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "LLENE TODOS LOS DATOS");
                    return;
                }

                try {
                    String cedula = cedulatxt.getText();
                    String nombre = nombretxt.getText();

                    // Crear SQL y preparar la consulta
                    String sql = "insert into Cliente ( cedula,nombre ) values ( ?, ?)";

                    try (Connection connection = DriverManager.getConnection(url, user, password);
                         PreparedStatement cadenaPreparada = connection.prepareStatement(sql)) {

                        cadenaPreparada.setString(1, cedula);
                        cadenaPreparada.setString(2, nombre);

                        cadenaPreparada.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Datos insertados correctamente");

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al insertar los datos.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de número incorrecto.");
                }
            }
        });

        Productobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form3();
                setVisible(false);
            }
        });

        irabuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new form4();
              setVisible(false);
            }
        });
    }

}