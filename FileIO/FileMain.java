package FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import ufogame.Ufo;

public class FileMain {

	
	public static void main(String[] args) {
		File file = new File("05Vorlesung\\fileIO\\name.txt");  //eine Datei, welche im JavaProjekt erstellt wird
		if(file.exists()) {				   //gucken ob die datei da ist und exitiert
			System.out.println("Die Datei existiert");
		} else {
			System.out.println("Die Datei wird angelegt"); //wenn nicht wird sie angelegt												
			try {                                          //versuchen sie zu erstellen
				file.createNewFile();
			} catch (IOException e) {  				//wenn was schieft läuft, wird der fehler abgefangen und das
				// TODO Auto-generated catch block  //Programm läuft normal weiter
				e.printStackTrace();
			}
			System.out.println("Das Prgramm lebt noch");
			
		}
		
	
		//Datei beschreiben mit FileWriter
		//file = Datei die wir oben geschrieben haben
			try (FileWriter fileWriter = new FileWriter(file, true); // true für append Flag, also dran hängen
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){ 
			
			bufferedWriter.write("Corona desinfiziert sich nach Kontakt mit Chuck Norris die Hände - BufferedWriter"); //Auf den FileWriter einen BufferWriter aufsetzten
			bufferedWriter.newLine();
			bufferedWriter.flush();
				
				
				//fileWriter.write("Corona desinfiziert sich nach Kontakt mit Chuck Norris die Hände \r\n");
				//fileWriter.flush(); //die daten werten auf jeden fall weggeschireben
				
			} catch(IOException e) {
				e.printStackTrace();

			}
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				String text = bufferedReader.readLine();
				System.out.println(text);
				
				bufferedReader.close();
				fileReader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			//versuche einen FIleOutputStream zu erstellen, so wie einen FileWriter nur in Byte aber geschireben hier
			try(FileOutputStream fileOutputStream = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {  // hier ObjectOutputStream erstellen bautauf auf  dem FileOutputStreams
					oos.writeObject(new Ufo(10, 15, 1000, 1000, 2, "spriteName"));
					oos.flush();
				} catch(IOException e) {
					e.printStackTrace();
				}
		}

	}
	
