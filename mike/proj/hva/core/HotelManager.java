package hva.core;

import hva.core.exception.*;
import java.io.*;

// FIXME import classes

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel;
  private boolean _hotelChanged = false;
  private String _filename;

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
    ObjectOutputStream out = null;
    try {
      FileOutputStream fOut = new FileOutputStream(_filename);
      out = new ObjectOutputStream(fOut);
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
    // FIXME implement serialization method
    ObjectOutputStream out = null;
    try {
      FileOutputStream fOut = new FileOutputStream(filename);
      out = new ObjectOutputStream(fOut);
      out.writeObject(_hotel);
    } finally {if (out != null) {out.close();}}
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
    // FIXME implement serialization method
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
      _hotel = (Hotel) in.readObject();
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
  public void importFile(String filename) throws ImportFileException, IOException, UnrecognizedEntryException, DuplicateFieldException, UnknownFieldException {
    _hotel = new Hotel();
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    if(_hotel == null) _hotel = new Hotel();
    return _hotel;
  }

  public final String getFileName() {return _filename;}
  public final boolean getHotelChanged() {return _hotelChanged;}
}
