/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * FixStemmer.java
 * Copyright (C) 2014  Aurelio Sanabria
 *
 */

package weka.core.stemmers;

import java.util.Vector;
import java.util.Enumeration;

import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.RevisionUtils;
import weka.core.Utils;

/**
 <!-- globalinfo-start -->
 * A simple stemmer that returns fix length stemms.
 * <p/>
 <!-- globalinfo-end -->
 * 
 * @author    Aurelio Sanabria   (sufrostico at gmail dot com)
 * @version   0.0.1 
 */
public class FixStemmer 
  implements Stemmer, OptionHandler {

  /** for serialization */
  static final long serialVersionUID = -3671261636532625494L;

  /** Length of the stem */
  protected int m_length = 7;

  /**
   * Returns an enumeration of all the available options..
   * 
   * @return an enumeration of all available options.
   */
  @Override
  public Enumeration<Option> listOptions() {
    Vector<Option> result = new Vector<Option>();

    result.addElement(new Option("\tLength of the stemm\n"
      + "\t(default 7).", "stemmlength", 1,
      "-stemmlength <value>"));

    return result.elements();
  }
  
  /**
   * Gets the current option settings for the OptionHandler.
   * 
   * @return the list of current option settings as an array of strings
   */
  @Override
  public String[] getOptions() {
    Vector<String> result = new Vector<String>();

    result.add("-stemmlength");
    result.add(getStemmlength()+"");

    return result.toArray(new String[result.size()]);
  }

  /**
   * Sets the OptionHandler's options using the given list. All options will be
   * set (or reset) during this call (i.e. incremental setting of options is not
   * possible).
   * 
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {

    String tmpStr = Utils.getOption("stemmlength", options);
    if (tmpStr.length() != 0) {
      setStemmlength(Integer.parseInt(tmpStr));
    } else {
      setStemmlength(7); // Default number always to 7
    }
  }

  /**
   * Get the value of delimiters (not backquoted).
   * 
   * @return Value of delimiters.
   */
  public int getStemmlength() {
    return m_length;
  }

  /**
   * --------
   */
  public void setStemmlength(int value) {
    m_length = value;
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String stemmlengthTipText() {
    return "Set of delimiter characters to use in tokenizing (\\r, \\n and \\t can be used for carriage-return, line-feed and tab)";
  }


  /**
   * Returns a string describing the stemmer
   * @return a description suitable for
   *         displaying in the explorer/experimenter gui
   */
  public String globalInfo() {
    return 
        "A simple stemmer that performs a fix length stemming.";
  }
  
  /**
   * Returns the word as it is.
   *
   * @param word      the unstemmed word
   * @return          the unstemmed word, again
   */
  public String stem(String word) {

    String result = new String(word);

    if (word.length() > getStemmlength()) {
      result = new String(word.substring(0,getStemmlength()));
    }

    return result;
  }

  /**
   * returns a string representation of the stemmer
   * 
   * @return a string representation of the stemmer
   */
  public String toString() {
    return getClass().getName();
  }
  
  /**
   * Returns the revision string.
   * 
   * @return		the revision
   */
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 0.0.1 $");
  }

  /**
   * Runs the stemmer with the given options
   *
   * @param args      the options
   */
  public static void main(String[] args) {
    try {
      Stemming.useStemmer(new FixStemmer(), args);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
