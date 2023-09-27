import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MiConversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox<moneda> cmb;
	private JLabel lbl;
	
	public enum moneda{
		peso_dolar,
		peso_euro,
		peso_libra,
		peso_yen,
		peso_wonsc,
		dolar_peso,
		euro_peso,
		libra_peso,
		yen_peso,
		wonsc_peso,
		
	}
	
	public double dolar = 17.02;
	public double euro = 18.26;
	public double libra = 21.12;
	public double yen = 0.12;
	public double wonsc = 0.013;
	
	public double valorInput = 0.00;
	private JTextField txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(10, 26, 111, 17);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(moneda.values()));
		cmb.setBounds(10, 69, 111, 21);
		frame.getContentPane().add(cmb);
		
		// evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(151, 69, 101, 21);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(165, 27, 87, 16);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir() {
		if(Validar(txt.getText())) {
			moneda moneda = (moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			case peso_dolar:
				pesoAmoneda(dolar);
				break;
			case peso_euro:
				pesoAmoneda(euro);
				break;
			case peso_libra:
				pesoAmoneda(libra);
				break;
			case peso_yen:
				pesoAmoneda(yen);
				break;
			case peso_wonsc:
				pesoAmoneda(wonsc);
				break;
			case dolar_peso:
				monedaApeso(dolar);
				break;
			case euro_peso:
				monedaApeso(euro);
				break;
			case libra_peso:
				monedaApeso(libra);
				break;
			case yen_peso:
				monedaApeso(yen);
				break;
			case wonsc_peso:
				monedaApeso(wonsc);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			}
		}
		
	}
	
	public void pesoAmoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	public void monedaApeso(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	//Redondear decimales
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	//validar Input
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x>0) {
		valorInput = x;
		return true;
		}}catch(NumberFormatException e) {
			lbl.setText("Solamente números !!");
			return false;
		}
		return false;	
	}
}
