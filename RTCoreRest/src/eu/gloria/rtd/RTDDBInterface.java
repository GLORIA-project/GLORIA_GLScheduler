package eu.gloria.rtd;

import java.util.List;

import eu.gloria.rt.entity.device.DbFileInfo;
import eu.gloria.rt.entity.device.DbFileMetadata;
import eu.gloria.rt.entity.device.DbFileSystemInfo;
import eu.gloria.rt.exception.RTException;

/**
 * This interfaces allows to access to Database.
 * 
 * @author jcabello
 *
 */
public interface RTDDBInterface extends RTDDeviceInterface {
	
	
	/**
	 * Returns True if the method GetFileContent is available.
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean dbIsAvailableDirectAccess() throws RTException;
	
	/**
	 * Returns True if the method GetFileChunk is available.
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean dbIsAvailableChunkAccess() throws RTException;
	
	/**
	 * Returns True if the method GetFileURI is available.
	 * 
	* @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean dbIsAvailableURIAccess() throws RTException;
	
	/**
	 * Returns the following file information:
	 * 
	 * -size (long). Used bytes on disk.
	 * -name (String). The file name (input).
	 * -fullname (String). Full name (path + filename) in the file system.
	 * 
     * @param folder Folder name
	 * @param fileName File name
	 * @return File information
	 * @throws RTException In error case.
	 */
	public DbFileInfo dbGetFileInfo(String folder, String fileName) throws RTException;
	
	/**
	 * Returns the whole file content.
	 * @param folder Folder name
	 * @param fileName File name.
	 * @return File content.
	 * @throws RTException In error case.
	 */
	public List<Byte> dbGetFileContent(String folder, String fileName) throws RTException;
	
	/**
	 * Returns a chunk of the file (byte[]), null if no more information to read.
	 * 
	 * @param fileName File name.
	 * @param offset The first byte to read.
	 * @param size ChunkSize.
	 * @return Read information.
	 * @throws RTException In error case.
	 */
	public List<Byte> dbGetFileChunk( String fileName, long offset, long size) throws RTException;
	
	/**
	 * Returns the file URL for external accesses (HTTP).
	 * @param fileName File name.
	 * @return URI
	 * @throws RTException In error case.
	 */
	public String dbGetFileURI( String fileName) throws RTException;
	
	/**
	 * Writes a file into the file system.
	 * @param fileName File name.
	 * @param content Whole content file.
	 * @param metadata List of metadata
	 * @throws RTException In error case.
	 */
	public void dbWriteFile( String fileName, List<Byte> content, List<DbFileMetadata> metadata) throws RTException;
	
	/**
	 * Returns the metadata associated to a file.
	 * 
	 * @param fileName File name
	 * @return List of metadata.
	 * @throws RTException In error case.
	 */
	public List<DbFileMetadata> dbGetMetadata( String fileName) throws RTException;
	
	/**
	 * Returns the file system information:
	 * -used (long). bytes used on disk.
	 * -free (long). bytes available on disk.
	 * @return File system information.
	 * @throws RTException In error case.
	 */
	public DbFileSystemInfo dbGetFileSystemInfo() throws RTException;
	
	/**
	 * Renames an existing file.
	 * @param currentFileName Current file name.
	 * @param newFileName New file name.
	 * @throws RTException In error case.
	 */
	public void dbRenameFile( String currentFileName, String newFileName) throws RTException;
	
	/**
	 * Deletes a file from D.B.
	 * @param fileName filename to remove.
	 * @throws RTException In error case.
	 */
	public void dbDeleteFile( String fileName) throws RTException;
	
	

}
