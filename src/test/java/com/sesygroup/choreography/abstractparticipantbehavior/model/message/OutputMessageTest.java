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
package com.sesygroup.choreography.abstractparticipantbehavior.model.message;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.sesygroup.choreography.abstractparticipantbehavior.model.Message;
import com.sesygroup.choreography.abstractparticipantbehavior.model.message.OutputMessage;

import org.junit.Assert;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class OutputMessageTest {
   private static Message mockedOutputMessage;
   private static OutputMessage outputMessage;

   @BeforeClass
   public static void setUp() {
      outputMessage = new OutputMessage("message");
      mockedOutputMessage = Mockito.mock(OutputMessage.class);
      Mockito.when(mockedOutputMessage.getName()).thenReturn("message");
      Mockito.when(mockedOutputMessage.toString()).thenReturn("message!");
   }

   @Test
   public void testGetName() {
      Assert.assertEquals(mockedOutputMessage.getName(), outputMessage.getName());
   }

   @Test
   public void testToString() {
      Assert.assertEquals(mockedOutputMessage.toString(), outputMessage.toString());
   }
}
