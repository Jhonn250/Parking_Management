/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author atlas
 */
public class AccessControl extends javax.swing.JFrame {

    Date date = new Date(); //Fecha (hora entrada)
    Date auxDate = new Date();//Fecha (hora salida)

    private Connection conexion = null; //Objeto de la clase connection
    private ResultSet result; //Objeto de la clase resultSet
    private Statement statement = null; //Objeto de la clase Statement
    private PreparedStatement ps = null; //Objeto de la clase Prepared Statement

    private String id = "";
    private String name = "";
    private String lastName = "";
    private String phone = "";
    private String typePerson = "";

    //Student
    private String career = "";
    private String semester = "";
    private String status = "";

    //Administrative
    private String area = "";

    //Teacher
    private String department = "";

    //Vehicle
    private String model = "";
    private String brand = ""; //marca
    private String type = ""; //auto-motocicleta
    private String plates = "";
    private String color = "";

    public AccessControl() {
        initComponents();
        this.setLocationRelativeTo(null);
        desactivate();
        defaultInfo();

    }

    public void connection() {
        String url = "jdbc:postgresql://localhost:5432/parkingManagement";
        /*Direccion por default en donde se
        encuentra la base de datos*/
        String user = "postgres";
        String password = ".Eduardo0309.";

        if (conexion != null) {
            return;
        }

        try {

            conexion = DriverManager.getConnection(url, user, password);

            if (conexion != null) //Verificacion para saber si se conecto correctamente
            {
                System.out.println("Conectando a la base de datos\n");

            }

        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage() + "\n");
        }
    }

    public boolean isValidInfoAdministrative() {
        boolean check = false;
        if (idText.getText().length() > 0) {
            if (nameText.getText().length() > 0) {
                if (lastText.getText().length() > 0) {
                    if (phoneText.getText().length() > 0 && phoneText.getText().length() == 8) {
                        if (ocupationComboBox.getSelectedIndex() != -1) {
                            if (areaComboBox.getSelectedIndex() != -1) {
                                check = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Error en Área de administrativo.");
                                areaComboBox.requestFocusInWindow();

                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Error en Ocupación.");
                            ocupationComboBox.requestFocusInWindow();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en Celular.");
                        statusComboBox.requestFocusInWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en Apellido.");
                    lastText.requestFocusInWindow();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error en Nombre.");
                nameText.requestFocusInWindow();
            }
        } else {

            JOptionPane.showMessageDialog(null, "Error en Código.");
            idText.requestFocusInWindow();
        }

        return check;
    }

    public void activate() {
        idText.setEnabled(true);
        nameText.setEnabled(true);
        lastText.setEnabled(true);
        phoneText.setEnabled(true);
        ocupationComboBox.setEnabled(true);
        careerText.setEnabled(true);
        statusComboBox.setSelectedIndex(-1);
        areaComboBox.setEnabled(true);
        departmentComboBox.setEnabled(true);
        brandText.setEnabled(true);

        platesText.setEnabled(true);
        modelText.setEnabled(true);
        colorText.setEnabled(true);
        typeVcomboBox.setEnabled(true);

    }

    public void desactivate() {
        idText.setEnabled(false);
        nameText.setEnabled(false);
        lastText.setEnabled(false);
        phoneText.setEnabled(false);
        ocupationComboBox.setEnabled(false);
        brandText.setEnabled(false);

        platesText.setEnabled(false);
        modelText.setEnabled(false);
        colorText.setEnabled(false);
        typeVcomboBox.setEnabled(false);

    }

    public void clearTxt() {
        
        searchText.setText("");
        
        idText.setText("");
        nameText.setText("");
        lastText.setText("");
        phoneText.setText("");
        ocupationComboBox.setSelectedIndex(-1);

        typeVcomboBox.setSelectedIndex(-1);
        platesText.setText("");
        brandText.setText("");
        colorText.setText("");
        modelText.setText("");
        careerText.setText("");
        statusComboBox.setSelectedIndex(-1);
        careerText.setText("");
        statusComboBox.setSelectedIndex(-1);


        departmentComboBox.setSelectedIndex(-1);
        areaComboBox.setSelectedIndex(-1);
    }

    public boolean isValidInfoTeacher() {
        boolean check = false;
        if (idText.getText().length() > 0) {
            if (nameText.getText().length() > 0) {
                if (lastText.getText().length() > 0) {
                    if (phoneText.getText().length() > 0 && phoneText.getText().length() == 8) {
                        if (ocupationComboBox.getSelectedIndex() != -1) {
                            if (departmentComboBox.getSelectedIndex() != -1) {
                                check = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Error en Departamento del profesor.");
                                departmentComboBox.requestFocusInWindow();

                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Error en Ocupación.");
                            ocupationComboBox.requestFocusInWindow();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en Celular.");
                        statusComboBox.requestFocusInWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en Apellido.");
                    lastText.requestFocusInWindow();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error en Nombre.");
                nameText.requestFocusInWindow();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error en Código.");
            idText.requestFocusInWindow();

        }

        return check;
    }

    public boolean isValidVehicle() {
        boolean check = false;
        if (typeVcomboBox.getSelectedIndex() != -1) {
            if (platesText.getText().length() > 0) {
                if (colorText.getText().length() > 0) {
                    if (modelText.getText().length() > 0) {
                        check = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en Modelo del vehículo.");
                        modelText.requestFocusInWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en Color del vehículo.");
                    colorText.requestFocusInWindow();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error en Placas del vehículo.");
                platesText.requestFocusInWindow();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error en Tipo del vehículo.");
            typeVcomboBox.requestFocusInWindow();
        }
        return check;
    }

    public boolean isValidInfoStudent() {
        boolean check = false;
        if (idText.getText().length() > 0) {
            if (nameText.getText().length() > 0) {
                if (lastText.getText().length() > 0) {
                    if (phoneText.getText().length() > 0 && phoneText.getText().length() == 8) {
                        if (ocupationComboBox.getSelectedIndex() != -1) {
                            if (careerText.getText().length() > 0) {
                                if (statusComboBox.getSelectedIndex() != -1) {
                                    check = true;

                                } else {
                                    JOptionPane.showMessageDialog(null, "Error en Status del alumno.");
                                    statusComboBox.requestFocusInWindow();
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Error en Carrera del alumno.");
                                careerText.requestFocusInWindow();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Error en Ocupación.");
                            ocupationComboBox.requestFocusInWindow();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en Celular.");
                        phoneText.requestFocusInWindow();

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en Apellido.");
                    lastText.requestFocusInWindow();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error en Nombre.");
                nameText.requestFocusInWindow();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error en Código.");
            idText.requestFocusInWindow();

        }

        return check;
    }

    public int indexComboBox() {
        return ocupationComboBox.getSelectedIndex();

    }

    public int existPerson() {
        int aux = 0;
        try {
            ps = conexion.prepareStatement("SELECT * FROM alumnos WHERE codigo_alumno = ?");
            ps.setString(1, searchText.getText());
            result = ps.executeQuery();
            if (result.next()) //Validar si hay un siguiente registro
            {
                aux = 1; //Estudiante
                idText.setEnabled(false);
                ocupationComboBox.setEnabled(false);
                typeVcomboBox.setEnabled(false);
                platesText.setEnabled(false);
            }

            ps = conexion.prepareStatement("SELECT * FROM profesores WHERE codigo_profesor = ?");
            ps.setString(1, searchText.getText());
            result = ps.executeQuery();
            if (result.next()) {
                aux = 2; //Maestro

                idText.setEnabled(false);
                ocupationComboBox.setEnabled(false);
                typeVcomboBox.setEnabled(false);
                platesText.setEnabled(false);
            }
            ps = conexion.prepareStatement("SELECT * FROM administrativos WHERE codigo_administrativo = ?");
            ps.setString(1, searchText.getText());
            result = ps.executeQuery();
            if (result.next()) {
                aux = 3;//Administ
                idText.setEnabled(false);
                ocupationComboBox.setEnabled(false);
                typeVcomboBox.setEnabled(false);
                platesText.setEnabled(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;

    }

    public void searchStudent() {
        boolean check = false;
        try {

            ps = conexion.prepareStatement("SELECT* FROM alumnos WHERE codigo_alumno = ?");
            ps.setString(1, searchText.getText());
            result = ps.executeQuery();

            if (result.next()) //Validar si hay un siguiente registro
            {
                idText.setText(result.getString("codigo_alumno"));
                nameText.setText(result.getString("nombre_est"));
                lastText.setText(result.getString("apellidos_est"));
                careerText.setText(result.getString("carrera"));
                statusComboBox.setSelectedItem(result.getString("estado_est"));
                phoneText.setText(result.getString("celular_est"));
                platesText.setText(result.getString("placas_vehiculo"));
                

            } else {
                JOptionPane.showMessageDialog(null, "No existe un ALUMNO con el código ingresado.");

            }
           

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void searchTeacher() {
        
        try {

            ps = conexion.prepareStatement("SELECT * FROM profesores WHERE codigo_profesor = ?");
            ps.setString(1, searchText.getText());
            result = ps.executeQuery();

            if (result.next()) //Validar si hay un siguiente registro
            {
                idText.setText(result.getString("codigo_profesor"));
                nameText.setText(result.getString("nombre_prof"));
                lastText.setText(result.getString("apellidos_prof"));
                departmentComboBox.setSelectedItem(result.getString("departamento"));
                phoneText.setText(result.getString("celular_prof"));
                platesText.setText(result.getString("placas_vehiculo"));
                

            } else {
                JOptionPane.showMessageDialog(null, "No existe un PROFESOR con el código ingresado");

            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public void searchAdministrative() {
       
        try {

            ps = conexion.prepareStatement("SELECT* FROM administrativos WHERE codigo_administrativo = ?");
            ps.setString(1, searchText.getText());
            result = ps.executeQuery();

            if (result.next()) //Validar si hay un siguiente registro
            {
                idText.setText(result.getString("codigo_administrativo"));
                phoneText.setText(result.getString("celular_adm"));
                areaComboBox.setSelectedItem(result.getString("area"));
                lastText.setText(result.getString("apellidos_adm"));
                nameText.setText(result.getString("nombre_adm"));
                platesText.setText(result.getString("placas_vehiculo"));
                
            }else
            {
                JOptionPane.showMessageDialog(null, "No existe un ADMNISTRATIVO con el código ingresado");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void updateStudent() {
        try {

            ps = conexion.prepareStatement("UPDATE alumnos SET nombre_est = ?, apellidos_est = ?"
                    + ", carrera = ?, estado_est = ?, celular_est = ?, placas_vehiculo = ? WHERE codigo_alumno = ?");

            ps.setString(1, nameText.getText());
            ps.setString(2, lastText.getText());
            ps.setString(3, careerText.getText());
            ps.setString(4, (String) statusComboBox.getSelectedItem());
            ps.setString(5, phoneText.getText());
            ps.setString(6, platesText.getText());
            ps.setString(7, idText.getText());

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "ALUMNO actualizado exitosamente");
                clearTxt();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar ALUMNO");
                clearTxt();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatetTeacher() {
        try {

            ps = conexion.prepareStatement("UPDATE alumnos SET nombre_prof = ?, apellidos_prof = ?"
                    + ", departamento = ?, celular_prof = ?, placas_vehiculo = ? WHERE codigo_profesor = ?");

            ps.setString(1, nameText.getText());
            ps.setString(2, lastText.getText());
            ps.setString(3, (String) departmentComboBox.getSelectedItem());
            ps.setString(4, phoneText.getText());
            ps.setString(5, platesText.getText());
            ps.setString(6, idText.getText());

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "PROFESOR actualizado exitosamente");
                clearTxt();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar PROFESOR");
                clearTxt();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAdministrative() {
        try {

            ps = conexion.prepareStatement("UPDATE administrative SET celular_adm = ?, area = ?"
                    + ", apellidos_adm = ?, nombre_adm = ?, placas_vehiculo = ? WHERE codigo_codigo_administrativo = ?");

            ps.setString(1, phoneText.getText());
            ps.setString(2, (String) areaComboBox.getSelectedItem());
            ps.setString(3, lastText.getText());
            ps.setString(4, nameText.getText());
            ps.setString(5, phoneText.getText());
            ps.setString(6, platesText.getText());
            ps.setString(7, idText.getText());

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "ADMINISTRATIVO actualizado exitosamente.");
                clearTxt();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar ADMINISTRATIVO.");
                clearTxt();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void defaultInfo() {
        //labelSemester.setVisible(false); 
        labelCareer.setVisible(false);
        labelState.setVisible(false);
        labelArea.setVisible(false);
        careerText.setVisible(false);
        statusComboBox.setVisible(false);

        labelArea.setVisible(false);
        areaComboBox.setVisible(false);

        labelDepartment.setVisible(false);
        departmentComboBox.setVisible(false);

    }

    public void hideStudentInfo() {
        //labelSemester.setVisible(false); 
        labelCareer.setVisible(false);
        labelState.setVisible(false);
        //semesterComboBox.setVisible(false);
        careerText.setVisible(false);
        statusComboBox.setVisible(false);
    }

    public void hideAdministrativeInfo() {
        areaComboBox.setVisible(false);
        labelArea.setVisible(false);

    }

    public void hideTeacherInfo() {
        labelDepartment.setVisible(false);
        departmentComboBox.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lastText = new javax.swing.JTextField();
        phoneText = new javax.swing.JTextField();
        careerText = new javax.swing.JTextField();
        idText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelCareer = new javax.swing.JLabel();
        labelState = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        ocupationComboBox = new javax.swing.JComboBox<>();
        labelArea = new javax.swing.JLabel();
        areaComboBox = new javax.swing.JComboBox<>();
        departmentComboBox = new javax.swing.JComboBox<>();
        labelDepartment = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        modelText = new javax.swing.JTextField();
        platesText = new javax.swing.JTextField();
        colorText = new javax.swing.JTextField();
        brandText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        typeVcomboBox = new javax.swing.JComboBox<>();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnCANCEL = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(nameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 220, 30));

        jLabel2.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REGISTRO:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 60));

        lastText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lastText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastTextActionPerformed(evt);
            }
        });
        getContentPane().add(lastText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 220, 30));

        phoneText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(phoneText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 220, 30));

        careerText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(careerText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 220, 30));

        idText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(idText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 220, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellidos:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 70, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Celular:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 70, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ocupación:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 70, 30));

        labelCareer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelCareer.setForeground(new java.awt.Color(255, 255, 255));
        labelCareer.setText("Carrera:");
        getContentPane().add(labelCareer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 80, 20));

        labelState.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelState.setForeground(new java.awt.Color(255, 255, 255));
        labelState.setText("Estado:");
        getContentPane().add(labelState, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 70, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Código:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, 30));

        statusComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(statusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 220, 30));

        ocupationComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ocupationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Profesor", "Administrativo", "Visitante" }));
        ocupationComboBox.setSelectedIndex(-1);
        ocupationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocupationComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(ocupationComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 220, 30));

        labelArea.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelArea.setForeground(new java.awt.Color(255, 255, 255));
        labelArea.setText("Área:");
        getContentPane().add(labelArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 70, 20));

        areaComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        areaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Control Escolar", "Orientación", "Coordinación" }));
        areaComboBox.setSelectedIndex(-1);
        getContentPane().add(areaComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 220, 30));

        departmentComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        departmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciencias y computación", "Física", "Matemáticas", "Industrial", "Mecánica Eléctrica", "Electrónica", "Química", "Farmacobiología", "Civil y Topografía" }));
        departmentComboBox.setSelectedIndex(-1);
        getContentPane().add(departmentComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 220, 30));

        labelDepartment.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelDepartment.setForeground(new java.awt.Color(255, 255, 255));
        labelDepartment.setText("Departamento:");
        getContentPane().add(labelDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 110, 30));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 255));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 255));
        jSeparator1.setToolTipText("");
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSeparator1.setOpaque(true);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 770, 10));

        modelText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(modelText, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 130, 30));

        platesText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        platesText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                platesTextActionPerformed(evt);
            }
        });
        getContentPane().add(platesText, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 130, 30));

        colorText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(colorText, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 130, 30));

        brandText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(brandText, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Modelo:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Placas:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Color:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Marca:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Vehículo:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, -1, -1));

        typeVcomboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        typeVcomboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Automóvil", "Motocicleta" }));
        typeVcomboBox.setSelectedIndex(-1);
        getContentPane().add(typeVcomboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 130, 30));

        btnInsert.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnInsert.setText("Insertar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 90, 30));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnUpdate.setText("Actualizar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, -1, 30));

        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });
        getContentPane().add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 210, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ingresa código a buscar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 160, 30));

        btnSearch.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 80, 30));

        btnNew.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnNew.setText("Nuevo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 90, 30));

        btnCANCEL.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnCANCEL.setText("Cancelar");
        btnCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELActionPerformed(evt);
            }
        });
        getContentPane().add(btnCANCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 450, -1, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/greyBg.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lastTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastTextActionPerformed

    private void ocupationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocupationComboBoxActionPerformed
        // TODO add your handling code here:

        if (ocupationComboBox.getSelectedIndex() == 0) {

            labelCareer.setVisible(true);
            labelState.setVisible(true);
            careerText.setVisible(true);
            statusComboBox.setVisible(true);

            hideTeacherInfo();
            hideAdministrativeInfo();

        } else if (ocupationComboBox.getSelectedIndex() == 1) {

            hideStudentInfo();
            hideAdministrativeInfo();

            labelDepartment.setVisible(true);
            departmentComboBox.setVisible(true);

        } else if (ocupationComboBox.getSelectedIndex() == 2) {
            hideTeacherInfo();
            hideStudentInfo();

            areaComboBox.setVisible(true);
            labelArea.setVisible(true);
        }

    }//GEN-LAST:event_ocupationComboBoxActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void platesTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_platesTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_platesTextActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        connection();
        try {

            id = idText.getText();
            name = nameText.getText();
            lastName = lastText.getText();
            phone = phoneText.getText();
            typePerson = (String) ocupationComboBox.getSelectedItem();

            career = careerText.getText();
            status = (String) statusComboBox.getSelectedItem();

            model = modelText.getText();
            plates = platesText.getText();
            brand = brandText.getText();
            color = colorText.getText();
            type = (String) typeVcomboBox.getSelectedItem();

            statement = conexion.createStatement();

            department = (String) departmentComboBox.getSelectedItem();
            area = (String) areaComboBox.getSelectedItem();
            if (indexComboBox() == 0 && isValidInfoStudent() && isValidVehicle()) {
                long time = date.getTime();
                int idPark = 0;
                Timestamp ts = new Timestamp(time);

                int alumno = statement.executeUpdate("INSERT INTO alumnos values ('" + id + "','" + name + "','" + lastName + "',"
                        + "'" + career + "', '" + status + "','" + phone + "','" + plates + "');"
                        + "");

                int vehicle = statement.executeUpdate("UPDATE vehiculos SET modelo = '" + model + "' , color = '" + color + "', tipo_vehiculo = '" + type + "'"
                        + "WHERE codigo_alumno = '" + idText.getText() + "';");

                long time2 = auxDate.getTime();
                Timestamp ts2 = new Timestamp(time2);

                int park = statement.executeUpdate("INSERT INTO estacionamiento (placas_vehiculo, hora_llegada, hora_salida, tipo) values ('" + plates + "','" + ts + "',"
                        + "'" + ts2 + "', '" + type + "');");

                if (alumno == 1) {
                    System.out.println("Registro agregado\n");
                    JOptionPane.showMessageDialog(null, "ESTUDIANTE agregado con éxito");
                    clearTxt();
                } else {
                    System.out.println("El registro no se pudo agregar\n");
                    clearTxt();
                }

            } else if (indexComboBox() == 1 && isValidInfoTeacher() && isValidVehicle()) {
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);

                int profesor = statement.executeUpdate("INSERT INTO profesores values ('" + id + "','" + name + "','" + lastName + "',"
                        + "'" + department + "', '" + phone + "','" + plates + "');");

                int vehicle = statement.executeUpdate("UPDATE vehiculos SET modelo = '" + model + "' , color = '" + color + "', tipo_vehiculo = '" + type + "'"
                        + "WHERE codigo_profesor = '" + idText.getText() + "';");

                long time2 = date.getTime();
                Timestamp ts2 = new Timestamp(time2);

                int park = statement.executeUpdate("INSERT INTO estacionamiento (placas_vehiculo, hora_llegada, hora_salida, tipo) values ('" + plates + "','" + ts + "',"
                        + "'" + ts2 + "', '" + type + "');");
                if (profesor == 1) {
                    System.out.println("Registro agregado\n");
                    JOptionPane.showMessageDialog(null, "PROFESOR agregado con éxito");
                    clearTxt();
                } else {
                    System.out.println("El registro no se pudo agregar\n");
                    clearTxt();
                }

            } else if (indexComboBox() == 2 && isValidInfoAdministrative() && isValidVehicle()) {
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);

                int administrativo = statement.executeUpdate("INSERT INTO administrativos values ('" + id + "','" + phone + "','" + area + "',"
                        + "'" + lastName + "', '" + name + "','" + plates + "');");

                int vehicle = statement.executeUpdate("UPDATE vehiculos SET modelo = '" + model + "' , color = '" + color + "', tipo_vehiculo = '" + type + "'"
                        + "WHERE codigo_administrativo = '" + idText.getText() + "';");

                long time2 = date.getTime();
                Timestamp ts2 = new Timestamp(time2);

                int park = statement.executeUpdate("INSERT INTO estacionamiento (placas_vehiculo, hora_llegada, hora_salida, tipo) values ('" + plates + "','" + ts + "',"
                        + "'" + ts2 + "', '" + type + "');");

                if (administrativo == 1) {
                    System.out.println("Registro agregado\n");
                    JOptionPane.showMessageDialog(null, "ADMINISTRATIVO agregado con éxito");
                    clearTxt();
                } else {
                    System.out.println("El registro no se pudo agregar\n");
                    clearTxt();
                }
            }

        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage() + "\n");
        }

    }

    private void showActionPerformed(java.awt.event.ActionEvent evt) {


    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        conexion = null;
        connection();
        if (indexComboBox() != -1) {
            if (indexComboBox() == 0) {
                searchStudent();
                idText.setEnabled(false);
                platesText.setEnabled(false);

            } else if (indexComboBox() == 1) {
                searchTeacher();
                idText.setEnabled(false);
                platesText.setEnabled(false);

            } else if (indexComboBox() == 2) {
                searchAdministrative();
                idText.setEnabled(false);
                platesText.setEnabled(false);

            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una ocupación.");
            ocupationComboBox.requestFocusInWindow();
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        connection();
        if (indexComboBox() != -1) {

            switch (existPerson()) {
                case 1:
                    updateStudent();
                    idText.setEnabled(true);
                    ocupationComboBox.setEnabled(true);
                    typeVcomboBox.setEnabled(true);
                    platesText.setEnabled(true);
                case 2:
                    updatetTeacher();
                    idText.setEnabled(true);
                    ocupationComboBox.setEnabled(true);
                    typeVcomboBox.setEnabled(true);
                    platesText.setEnabled(true);
                case 3:
                    updateAdministrative();

                    idText.setEnabled(true);
                    ocupationComboBox.setEnabled(true);
                    typeVcomboBox.setEnabled(true);
                    platesText.setEnabled(true);
                    
                default:
                    clearTxt();

            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor selecciona una ocupación.");
            ocupationComboBox.requestFocusInWindow();
        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        activate();
        clearTxt();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        // TODO add your handling code here:
        desactivate();
    }//GEN-LAST:event_btnCANCELActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccessControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccessControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccessControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccessControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccessControl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> areaComboBox;
    private javax.swing.JTextField brandText;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField careerText;
    private javax.swing.JTextField colorText;
    private javax.swing.JComboBox<String> departmentComboBox;
    private javax.swing.JTextField idText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelArea;
    private javax.swing.JLabel labelCareer;
    private javax.swing.JLabel labelDepartment;
    private javax.swing.JLabel labelState;
    private javax.swing.JTextField lastText;
    private javax.swing.JTextField modelText;
    private javax.swing.JTextField nameText;
    private javax.swing.JComboBox<String> ocupationComboBox;
    private javax.swing.JTextField phoneText;
    private javax.swing.JTextField platesText;
    private javax.swing.JTextField searchText;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JComboBox<String> typeVcomboBox;
    // End of variables declaration//GEN-END:variables
}
