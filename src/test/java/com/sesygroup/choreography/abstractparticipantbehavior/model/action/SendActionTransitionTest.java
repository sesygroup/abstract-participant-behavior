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
package com.sesygroup.choreography.abstractparticipantbehavior.model.action;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.sesygroup.choreography.abstractparticipantbehavior.model.State;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.SendActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.message.OutputMessage;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class SendActionTransitionTest {
   private static SendActionTransition mockedSendAction;
   private static SendActionTransition sendAction;

   @BeforeClass
   public static void setUp() {
      State sourceState = new State("source");
      State targetState = new State("target");
      OutputMessage outputMessage = new OutputMessage("message");
      sendAction = new SendActionTransition(sourceState, targetState, outputMessage);
      mockedSendAction = Mockito.mock(SendActionTransition.class);
      Mockito.when(mockedSendAction.getSourceState()).thenReturn(sourceState);
      Mockito.when(mockedSendAction.getTargetState()).thenReturn(targetState);
      Mockito.when(mockedSendAction.getOutputMessage()).thenReturn(outputMessage);
      Mockito.when(mockedSendAction.toString())
            .thenReturn("(source, message!, target)");
   }

   @Test
   public void testGetSourceState() {
      Assert.assertEquals(mockedSendAction.getSourceState(), sendAction.getSourceState());
   }

   @Test
   public void testGetTargetState() {
      Assert.assertEquals(mockedSendAction.getTargetState(), sendAction.getTargetState());
   }

   @Test
   public void testGetOutputMessage() {
      Assert.assertEquals(mockedSendAction.getOutputMessage(), sendAction.getOutputMessage());
   }

   @Test
   public void testToString() {
      Assert.assertEquals(mockedSendAction.toString(), sendAction.toString());
   }
}
