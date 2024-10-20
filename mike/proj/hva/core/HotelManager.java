package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  private Hotel _hotel = new Hotel();
  private String _filename;

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_filename == null) {
      throw new MissingFileAssociationException();
    }
    ObjectOutputStream out = null;
    try {
      FileOutputStream fOut = new FileOutputStream(_filename);
      out = new ObjectOutputStream(fOut);
      this.getHotel().setChanged(false);
      out.writeObject(_hotel);
    } finally {if (out != null) {out.close();}}
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (filename == null) {
      throw new MissingFileAssociationException();
    }
    _filename = filename;
    save();
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
      _hotel = (Hotel) in.readObject();
  } catch (IOException | ClassNotFoundException ex) {
      throw new UnavailableFileException(filename);
  }
}
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel.importFile(filename);
      _hotel.setChanged(true);
    } catch (IOException | UnrecognizedEntryException | InvalidInputException ex) {
      throw new ImportFileException(filename, ex);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  /**
   * Returns the name of the file associated with the current hotel.
   * 
   * @return the file name as a String, or null if no file is associated
   */
  public final String getFileName(){
    return _filename;
  }

  /**
   * Creates a new Hotel instance and resets the file name associated with the hotel to null.
   */
  public void createHotel(){  
    _hotel = new Hotel();
    _filename = null;
  }
}

