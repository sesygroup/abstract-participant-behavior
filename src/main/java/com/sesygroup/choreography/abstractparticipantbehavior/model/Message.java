/*
 * Copyright 2017 Software Engineering and Synthesis Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sesygroup.choreography.abstractparticipantbehavior.model;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public abstract class Message {
   protected String name;

   public Message() {
      super();
   }

   public Message(final String name) {
      super();
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(final String name) {
      this.name = name;
   }

   @Override
   public abstract int hashCode();

   @Override
   public abstract boolean equals(Object obj);

   @Override
   public abstract String toString();

}
