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
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.ReceiveActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.message.InputMessage;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class ReceiveActionTransitionTest {
   private static ReceiveActionTransition mockedReceiveAction;
   private static ReceiveActionTransition receiveAction;

   @BeforeClass
   public static void setUp() {
      State sourceState = new State("source");
      State targetState = new State("target");
      InputMessage inputMessage = new InputMessage("message");
      receiveAction = new ReceiveActionTransition(sourceState, targetState, inputMessage);
      mockedReceiveAction = Mockito.mock(ReceiveActionTransition.class);
      Mockito.when(mockedReceiveAction.getSourceState()).thenReturn(sourceState);
      Mockito.when(mockedReceiveAction.getTargetState()).thenReturn(targetState);
      Mockito.when(mockedReceiveAction.getInputMessage()).thenReturn(inputMessage);
      Mockito.when(mockedReceiveAction.toString())
            .thenReturn("(source, message?, target)");
   }

   @Test
   public void testGetSourceState() {
      Assert.assertEquals(mockedReceiveAction.getSourceState(), receiveAction.getSourceState());
   }

   @Test
   public void testGetTargetState() {
      Assert.assertEquals(mockedReceiveAction.getTargetState(), receiveAction.getTargetState());
   }

   @Test
   public void testGetInputMessage() {
      Assert.assertEquals(mockedReceiveAction.getInputMessage(), receiveAction.getInputMessage());
   }

   @Test
   public void testToString() {
      Assert.assertEquals(mockedReceiveAction.toString(), receiveAction.toString());
   }
}
