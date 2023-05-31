package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import javax.swing.JTextField;

import model.Grupos;
public class GruposController implements ActionListener {
	private  JTextField textFieldNOMEGP;
	private  JTextField textFieldTEMAGP;
	private  JTextField textFieldAREA;
	private  JTextField textFieldSUBAREA;
	private  JTextField textFieldDATA;
	private  JTextField textFieldCOD;
	private  JTextField textField_ra01;
	private  JTextField textField_ra02;
	private  JTextField textField_ra03;
	private  JTextField textField_ra04;
	
	
	
	public GruposController(JTextField textFieldNOMEGP, JTextField textFieldTEMAGP, JTextField textFieldAREA,
			JTextField textFieldSUBAREA, JTextField textFieldDATA, JTextField textFieldCOD,
			JTextField textField_ra01, JTextField textField_ra02, JTextField textField_ra03, JTextField textField_ra04  ) {
		
		super();
		this.textFieldNOMEGP = textFieldNOMEGP;
		this.textFieldTEMAGP = textFieldTEMAGP;
		this.textFieldAREA = textFieldAREA;
		this.textFieldSUBAREA = textFieldSUBAREA;
		this.textFieldDATA = textFieldDATA;
		this.textFieldCOD = textFieldCOD;
		this.textField_ra01 = textField_ra01;
		this.textField_ra02 = textField_ra02;
		this.textField_ra03 = textField_ra03;
		this.textField_ra04 = textField_ra04;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Cadastrar")) {
				try {
					cadastro();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		
		}
	}
	



	private void cadastro() throws IOException {
		Grupos grupo=new Grupos();
		grupo.nome=textFieldNOMEGP.getText();
		grupo.tema=textFieldTEMAGP.getText();
		grupo.area=textFieldAREA.getText();
		grupo.subarea=textFieldSUBAREA.getText();
		grupo.data=textFieldDATA.getText();
		grupo.codigo=textFieldCOD.getText();
		grupo.Ra1 = textField_ra01.getText();
		grupo.Ra2 = textField_ra02.getText();
		grupo.Ra3 = textField_ra03.getText();
		grupo.Ra4 = textField_ra04.getText();
	    System.out.println(grupo);
		
		Cadastragrupos(grupo.toString());
		textFieldNOMEGP.setText("");
		textFieldTEMAGP.setText("");
		textFieldAREA.setText("");
		textFieldSUBAREA.setText("");
		textFieldDATA.setText("");
		textFieldCOD.setText("");
		textField_ra01.setText("");
		textField_ra02.setText("");
		textField_ra03.setText("");
		textField_ra04.setText("");
		}



	private void Cadastragrupos(String csvGrupos) throws IOException {
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
			File dir= new File(path);
			if(!dir.exists()) {
				dir.mkdir();
				
			}
			File arq= new File(path, "grupos.csv");
			boolean existe=false;
			if(arq.exists()) {
				existe=true;
			}
			FileWriter fw= new FileWriter(arq, existe);
			
			PrintWriter pw= new PrintWriter(fw);
			pw.write(csvGrupos+"\r\n");
			pw.flush();
			pw.close();
			fw.close();
			
			
		}




}
