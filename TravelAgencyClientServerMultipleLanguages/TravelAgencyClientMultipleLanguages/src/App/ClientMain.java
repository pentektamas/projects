package App;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.UIManager;
import contract.IAvionDAC;
import contract.IBiletDAC;
import contract.ICalatorDAC;
import contract.IContUtilizatorDAC;
import contract.IPersoanaDAC;
import contract.IUtilizatorDAC;
import controller.GeneralController;
import model.RBundle;

public class ClientMain {

	public static void setUIFont() {
		UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Arial", Font.BOLD, 14));
	}

	private static void setLookAndFeel() {
		UIManager.put("control", new Color(48, 45, 45));
		UIManager.put("info", new Color(0, 0, 0));
		UIManager.put("nimbusBase", new Color(18, 30, 49));
		UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
		UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
		UIManager.put("nimbusFocus", new Color(255, 0, 0));
		UIManager.put("nimbusGreen", new Color(176, 179, 50));
		UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
		UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
		UIManager.put("nimbusOrange", new Color(191, 98, 4));
		UIManager.put("nimbusRed", new Color(169, 46, 34));
		UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
		UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
		UIManager.put("text", new Color(255, 255, 255));
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 1234);
			Registry registry = LocateRegistry.getRegistry(null, 1099);
			IAvionDAC stubAvion = (IAvionDAC) registry.lookup("model.IAvionDAC");
			IBiletDAC stubBilet = (IBiletDAC) registry.lookup("model.IBiletDAC");
			ICalatorDAC stubCalator = (ICalatorDAC) registry.lookup("model.ICalatorDAC");
			IContUtilizatorDAC stubContUtilizator = (IContUtilizatorDAC) registry.lookup("model.IContUtilizatorDAC");
			IPersoanaDAC stubPersoana = (IPersoanaDAC) registry.lookup("model.IPersoanaDAC");
			IUtilizatorDAC stubUtilizator = (IUtilizatorDAC) registry.lookup("model.IUtilizatorDAC");
			setLookAndFeel();
			setUIFont();
			new GeneralController(socket, stubAvion, stubBilet, stubCalator, stubContUtilizator, stubPersoana,
					stubUtilizator, new RBundle());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to create client socket!");
			e.printStackTrace();
		}
	}

}
