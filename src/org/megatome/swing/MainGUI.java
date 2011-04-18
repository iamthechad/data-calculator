package org.megatome.swing;

/*
 * Copyright 2011 Megatome Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.*;

import org.megatome.data.DataTypes;
import org.megatome.data.IDataType;
import org.megatome.util.ImageLoader;
import org.megatome.util.Messages;
import org.megatome.util.Operation;

import com.megatome.swing.MacOSEventHandler;
import com.megatome.swing.MacOSEventListener;

public final class MainGUI extends JFrame implements ActionListener, MacOSEventListener {

    private static final long serialVersionUID = 6910204337314164435L;

    protected JFormattedTextField valueJTF = null;

    private JButton clearButton = null;

    private JButton addButton = null;

    private JButton subtractButton = null;

    private JButton equalsButton = null;

    protected DataDetailsDialog detailsDlg = null;

    private float totalValue = 0F;

    protected Operation currentOperation = Operation.NONE;

    protected float displayedValue = 0F;

    protected float previousValue = 0F;

    private static final Insets insets_5_5_5_5 = new Insets(5, 5, 5, 5);

    private static final Insets insets_0_5_0_0 = new Insets(0, 5, 0, 0);

    private static final Insets insets_10_5_0_0 = new Insets(10, 5, 0, 0);

    /**
     * This is the default constructor
     */
    public MainGUI() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * @return void
     */
    private void initialize() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(@SuppressWarnings("unused")
            WindowEvent evt) {
                closeGUI();
            }
        });
        this.initializeUI();
        this.setTitle(Messages.getString("MainGUI.title")); //$NON-NLS-1$
        this.setIconImage(ImageLoader.getImage(Messages.getString("mainIcon"))); //$NON-NLS-1$
        initAndShowDetailsDlg();
    }

    private void initAndShowDetailsDlg() {
        if (this.detailsDlg == null) {
            this.detailsDlg = new DataDetailsDialog(this);
        }

        this.detailsDlg.setVisible(true);
    }

    protected void closeGUI() {
        if (this.detailsDlg != null) {
            this.detailsDlg.dispose();
            this.detailsDlg = null;
        }
        this.dispose();
    }

    private void initializeUI() {
        boolean isMacOS = (System.getProperty("mrj.version") != null); //$NON-NLS-1$
        buildMenus(isMacOS);
        int width = 490;
        int height = 660;
        // Kind of a dumb hack....
        if (!isMacOS) {
            height += 10;
        }
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        NumberFormat displayFormat = NumberFormat.getCurrencyInstance();
        displayFormat.setMinimumFractionDigits(2);
        this.valueJTF = new JFormattedTextField(new DefaultFormatterFactory(
                new NumberFormatter(displayFormat), new NumberFormatter(
                        displayFormat), new NumberFormatter(displayFormat)));
        this.valueJTF.setFont(new Font("Dialog", Font.PLAIN, 24)); //$NON-NLS-1$
        this.valueJTF.setEditable(false);
        this.valueJTF.setText(""); //$NON-NLS-1$
        this.valueJTF.setHorizontalAlignment(SwingConstants.RIGHT);
        //this.valueJTF.setPreferredSize(new Dimension(138, 10));
        contentPane.add(this.valueJTF, null);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        // Information Buttons
        JLabel generalInfoLabel = new JLabel(Messages
                .getString("MainGUI.generalInformationLabel")); //$NON-NLS-1$
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = insets_0_5_0_0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(generalInfoLabel, gridBagConstraints);

        buildAndAddDataButton(DataTypes.ADDRESS.getDataType(), 0, 1, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.ZIPCODE.getDataType(), 1, 1, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.PAST_ADDRESSES.getDataType(), 2, 1, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.DATE_OF_BIRTH.getDataType(), 3, 1, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.MARRIAGE.getDataType(), 0, 2, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.DIVORCE.getDataType(), 1, 2, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.EDUCATION.getDataType(), 2, 2, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.EMPLOYMENT.getDataType(), 3, 2, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.PUBLISHED_PHONE.getDataType(), 0, 3, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.UNPUBLISHED_PHONE.getDataType(), 1, 3, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.CELLULAR_PHONE.getDataType(), 2, 3, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.PAST_PHONES.getDataType(), 3, 3, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.RELATIVES.getDataType(), 0, 4, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.NEIGHBORS.getDataType(), 1, 4, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.REGISTERED_URL.getDataType(), 2, 4, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.SSN.getDataType(), 3, 4, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);

        // Financial buttons
        JLabel financialLabel = new JLabel(Messages
                .getString("MainGUI.financialLabel")); //$NON-NLS-1$
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = insets_10_5_0_0;
        jPanel.add(financialLabel, gridBagConstraints);

        buildAndAddDataButton(DataTypes.CREDIT.getDataType(), 0, 6, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.REAL_ESTATE.getDataType(), 1, 6, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.BANKRUPTCY.getDataType(), 2, 6, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.WORKERS_COMP.getDataType(), 3, 6, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.ASSETS.getDataType(), 0, 7, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.ASSETS_SEIZED.getDataType(), 1, 7, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.SHAREHOLDER.getDataType(), 2, 7, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.EXECUTIVE_AFFILIATION.getDataType(), 3, 7,
                insets_5_5_5_5, GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.OWN_AIRCRAFT.getDataType(), 0, 8, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.OWN_BOAT.getDataType(), 1, 8, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.OWN_VEHICLE.getDataType(), 2, 8, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.OWN_BUSINESS.getDataType(), 3, 8, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);

        // Legal Buttons
        JLabel legalLabel = new JLabel(Messages.getString("MainGUI.legalLabel")); //$NON-NLS-1$
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = insets_10_5_0_0;
        jPanel.add(legalLabel, gridBagConstraints);

        buildAndAddDataButton(DataTypes.LAWSUITS.getDataType(), 0, 10, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.FELONY.getDataType(), 1, 10, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.MISDEMEANOR.getDataType(), 2, 10, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.SEX_OFFENDER.getDataType(), 3, 10, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);

        // License Buttons
        JLabel licenseLabel = new JLabel(Messages
                .getString("MainGUI.licenseLabel")); //$NON-NLS-1$
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = insets_10_5_0_0;
        gridBagConstraints.gridwidth = 4;
        jPanel.add(licenseLabel, gridBagConstraints);

        buildAndAddDataButton(DataTypes.DRIVER_LICENSE.getDataType(), 0, 12, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.MOTOR_VEHICLE.getDataType(), 1, 12, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.LIST_OF_VEHICLES.getDataType(), 2, 12, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.ACCIDENT_RECORD.getDataType(), 3, 12, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.AIRCRAFT_LICENSE.getDataType(), 0, 13, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.DEA_LICENSE.getDataType(), 1, 13, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.HUNT_FISH_LICENSE.getDataType(), 2, 13,
                insets_5_5_5_5, GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.PROFESSIONAL_LICENSE.getDataType(), 3, 13,
                insets_5_5_5_5, GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.INDUST_ACCRED.getDataType(), 0, 14, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.MERCHANT_VESSEL.getDataType(), 1, 14, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.CONCEAL_WEAPON.getDataType(), 2, 14, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.FIREARMS_LICENSE.getDataType(), 3, 14, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);

        // Political Buttons
        JLabel politicalLabel = new JLabel(Messages
                .getString("MainGUI.politLabel")); //$NON-NLS-1$
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = insets_10_5_0_0;
        gridBagConstraints.gridwidth = 4;
        jPanel.add(politicalLabel, gridBagConstraints);

        buildAndAddDataButton(DataTypes.VOTER_REGISTRATION.getDataType(), 0, 16,
                insets_5_5_5_5, GridBagConstraints.HORIZONTAL, jPanel);
        buildAndAddDataButton(DataTypes.MILITARY_RECORD.getDataType(), 1, 16, insets_5_5_5_5,
                GridBagConstraints.HORIZONTAL, jPanel);

        // Operation button panel
        JPanel opsButtonPanel = new JPanel();
        opsButtonPanel.setLayout(new GridBagLayout());

        this.clearButton = new JButton();
        this.clearButton.setText("C"); //$NON-NLS-1$
        this.clearButton.setFont(new Font("Dialog", Font.BOLD, 36)); //$NON-NLS-1$
        this.clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(@SuppressWarnings("unused")
            ActionEvent evt) {
                // valueJTF.setText("");
                MainGUI.this.valueJTF.setValue(null);
                MainGUI.this.detailsDlg.clearDetails();
                MainGUI.this.displayedValue = 0F;
                MainGUI.this.previousValue = 0F;
                MainGUI.this.currentOperation = Operation.NONE;
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 25);
        opsButtonPanel.add(this.clearButton, gridBagConstraints);

        this.addButton = new JButton();
        this.addButton.setText("+"); //$NON-NLS-1$
        this.addButton.setFont(new Font("Dialog", Font.BOLD, 36)); //$NON-NLS-1$
        this.addButton.addActionListener(new ActionListener() {

            public void actionPerformed(@SuppressWarnings("unused")
            ActionEvent evt) {
                if (MainGUI.this.displayedValue == 0F)
                    return;

                MainGUI.this.currentOperation = Operation.ADD;

                if (MainGUI.this.previousValue == 0F)
                    return;

                updateValues(MainGUI.this.previousValue,
                        MainGUI.this.displayedValue);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 25, 0, 25);
        opsButtonPanel.add(this.addButton, gridBagConstraints);

        this.subtractButton = new JButton();
        this.subtractButton.setText("-"); //$NON-NLS-1$
        this.subtractButton.setFont(new Font("Dialog", Font.BOLD, 36)); //$NON-NLS-1$
        this.subtractButton.addActionListener(new ActionListener() {

            public void actionPerformed(@SuppressWarnings("unused")
            ActionEvent evt) {
                if (MainGUI.this.displayedValue == 0F)
                    return;

                MainGUI.this.currentOperation = Operation.SUBTRACT;

                if (MainGUI.this.previousValue == 0F)
                    return;

                updateValues(MainGUI.this.previousValue,
                        MainGUI.this.displayedValue);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 25, 0, 25);
        opsButtonPanel.add(this.subtractButton, gridBagConstraints);

        this.equalsButton = new JButton();
        this.equalsButton.setText("="); //$NON-NLS-1$
        this.equalsButton.setFont(new Font("Dialog", Font.BOLD, 36)); //$NON-NLS-1$
        this.equalsButton.addActionListener(new ActionListener() {

            public void actionPerformed(@SuppressWarnings("unused")
            ActionEvent evt) {
                if ((MainGUI.this.displayedValue == 0F)
                        || (MainGUI.this.previousValue == 0F))
                    return;

                updateValues(MainGUI.this.previousValue,
                        MainGUI.this.displayedValue);
                MainGUI.this.currentOperation = Operation.NONE;
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 25, 0, 0);
        opsButtonPanel.add(this.equalsButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        jPanel.add(opsButtonPanel, gridBagConstraints);

        contentPane.add(jPanel, null);
        this.setContentPane(contentPane);
        this.pack();
    }

    private DataButton buildAndAddDataButton(IDataType dataType, int gridx,
            int gridy, Insets insets, int gridFill, JPanel panel) {
        DataButton button = new DataButton(dataType, this);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.insets = insets;
        gridBagConstraints.fill = gridFill;
        panel.add(button, gridBagConstraints);
        return button;
    }

    private void buildMenus(boolean isMacOS) {
        if (isMacOS) {
            // Do something
            new MacOSEventHandler(this, false);
        } else {
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu(Messages
                    .getString("MainGUI.fileMenuTitle")); //$NON-NLS-1$
            JMenuItem exitMenuItem = new JMenuItem(Messages
                    .getString("MainGUI.exitItemTitle")); //$NON-NLS-1$
            exitMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused")
                ActionEvent evt) {
                    closeGUI();
                }
            });
            fileMenu.add(exitMenuItem);
            menuBar.add(fileMenu);
            JMenu helpMenu = new JMenu(Messages
                    .getString("MainGUI.helpMenuTitle")); //$NON-NLS-1$
            JMenuItem aboutMenuItem = new JMenuItem(Messages
                    .getString("MainGUI.aboutItemTitle")); //$NON-NLS-1$
            aboutMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused")
                ActionEvent evt) {
                    showAboutDialog();
                }
            });
            helpMenu.add(aboutMenuItem);
            menuBar.add(helpMenu);
            this.setJMenuBar(menuBar);
        }
    }

    public void showAboutDialog() {
        String aboutText = Messages.getString("MainGUI.aboutText"); //$NON-NLS-1$
        JOptionPane.showMessageDialog(this, aboutText, Messages.getString("MainGUI.aboutTitle"), //$NON-NLS-1$
                JOptionPane.PLAIN_MESSAGE, ImageLoader.getImageIcon(Messages.getString("aboutIcon"))); //$NON-NLS-1$
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof DataButton) {
            DataButton db = (DataButton)evt.getSource();
            this.previousValue = this.displayedValue;
            IDataType dt = db.getDataType();
            this.displayedValue = Float.parseFloat(dt.getDataValue());
            this.valueJTF.setValue(Float.valueOf(this.displayedValue));
            if (this.detailsDlg != null) {
                this.detailsDlg.updateDetails(dt.getImagePath(), dt
                        .toHTMLString());
            }
        }
    }

    protected void updateValues(float operand1, float operand2) {
        this.totalValue = performCalculation(operand1, operand2,
                this.currentOperation);
        this.displayedValue = this.totalValue;
        this.previousValue = 0F;
        this.valueJTF.setValue(Float.valueOf(this.totalValue));
    }

    private float performCalculation(float operand1, float operand2,
            Operation operation) {
        float result = 0F;
        if (Operation.ADD.equals(operation)) {
            result = operand1 + operand2;
        } else if (Operation.SUBTRACT.equals(operation)) {
            result = operand1 - operand2;
        }

        return result;
    }

    public boolean doQuit() {
        return true;
    }

    public void handlePreferences() {
        // No preferences
    }
}