/*
 *
 *  *
 *  *  * Copyright 2015 Skymind,Inc.
 *  *  *
 *  *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *    you may not use this file except in compliance with the License.
 *  *  *    You may obtain a copy of the License at
 *  *  *
 *  *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  *    Unless required by applicable law or agreed to in writing, software
 *  *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *    See the License for the specific language governing permissions and
 *  *  *    limitations under the License.
 *  *
 *
 */

package org.canova.nlp.movingwindow;


import org.canova.api.berkeley.Counter;
import org.canova.api.berkeley.MapFactory;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Util {

  /**
   * Returns a thread safe counter
   *
   * @return
   */
  public static Counter<String> parallelCounter() {
    MapFactory<String, Double> factory = new MapFactory<String, Double>() {

      private static final long serialVersionUID = 5447027920163740307L;

      @Override
      public Map<String, Double> buildMap() {
        return new java.util.concurrent.ConcurrentHashMap<>();
      }

    };

    return new Counter<>(factory);
  }

  public static boolean matchesAnyStopWord(List<String> stopWords, String word) {
    for (String s : stopWords)
      if (s.equalsIgnoreCase(word))
        return true;
    return false;
  }

  public static Level disableLogging() {
    Logger logger = Logger.getLogger("org.apache.uima");
    while (logger.getLevel() == null) {
      logger = logger.getParent();
    }
    Level level = logger.getLevel();
    logger.setLevel(Level.OFF);
    return level;
  }


}
