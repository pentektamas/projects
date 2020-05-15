package App;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import contract.*;
import dataAcces.*;

public class ServerMain {

	private static ServerSocket serverSocket;
	private static List<Socket> sockets = new ArrayList<Socket>();
	private static Registry registry;
	private static Thread t = new Thread() {

		@Override
		public void run() {

			try {
				serverSocket = new ServerSocket(1234);
				registry = LocateRegistry.createRegistry(1099);
				System.out.println("Server is listening on port: " + serverSocket.getLocalPort());
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			while (true) {
				try {
					Socket socket = serverSocket.accept();
					sockets.add(socket);
					System.out.println("New client connected with success!");

					IAvionDAC avionStub = new AvionDAC();
					IBiletDAC biletStub = new BiletDAC();
					ICalatorDAC calatorStub = new CalatorDAC();
					IContUtilizatorDAC contUtilizatorStub = new ContUtilizatorDAC();
					IPersoanaDAC persoanaStub = new PersoanaDAC();
					IUtilizatorDAC utilizatorStub = new UtilizatorDAC();

					IAvionDAC stubAvion = (IAvionDAC) UnicastRemoteObject.exportObject((IAvionDAC) avionStub, 1);
					IUtilizatorDAC stubUtilizator = (IUtilizatorDAC) UnicastRemoteObject
							.exportObject((IUtilizatorDAC) utilizatorStub, 2);
					IContUtilizatorDAC stubContUtilizator = (IContUtilizatorDAC) UnicastRemoteObject
							.exportObject((IContUtilizatorDAC) contUtilizatorStub, 3);
					IBiletDAC stubBilet = (IBiletDAC) UnicastRemoteObject.exportObject((IBiletDAC) biletStub, 4);
					ICalatorDAC stubCalator = (ICalatorDAC) UnicastRemoteObject.exportObject((ICalatorDAC) calatorStub,
							5);
					IPersoanaDAC stubPersoana = (IPersoanaDAC) UnicastRemoteObject
							.exportObject((IPersoanaDAC) persoanaStub, 6);

					registry.rebind("model.IAvionDAC", stubAvion);
					registry.rebind("model.IUtilizatorDAC", stubUtilizator);
					registry.rebind("model.IContUtilizatorDAC", stubContUtilizator);
					registry.rebind("model.IBiletDAC", stubBilet);
					registry.rebind("model.ICalatorDAC", stubCalator);
					registry.rebind("model.IPersoanaDAC", stubPersoana);

				} catch (Exception e) {
				}
			}
		}
	};

	public static void main(String[] args) {
		t.start();
		Scanner scanner = new Scanner(System.in);
		String stopCommand = "";

		while (true) {
			if (stopCommand.equals("STOP")) {
				try {
					UnicastRemoteObject.unexportObject(registry, true);
					for (Socket socket : sockets) {
						socket.close();
					}
					serverSocket.close();
					scanner.close();
					System.exit(0);
				} catch (Exception e) {
					System.out.println("Error while stopping the server!");
				}
			}
			stopCommand = scanner.next();
		}
	}
}
