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
package com.sesygroup.choreography.abstractparticipantbehavior.generator;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.sesygroup.choreography.choreographyspecification.model.ChoreographySpecification;
import com.sesygroup.choreography.choreographyspecification.model.Participant;
import com.sesygroup.choreography.choreographyspecification.model.Transition;
import com.sesygroup.choreography.choreographyspecification.model.action.SendingMessageActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.State;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.InternalActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.ReceiveActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.SendActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.message.InputMessage;
import com.sesygroup.choreography.abstractparticipantbehavior.model.message.OutputMessage;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class ChoreographyProjectionUtils {

   public static Collection<InternalActionTransition> findInternalActionTransitions(
         final ChoreographySpecification choreographySpecification, final Participant participant) {

      return CollectionUtils.select(choreographySpecification.getTransitions(), new Predicate<Transition>() {
         @Override
         public boolean evaluate(final Transition object) {
            return SendingMessageActionTransition.class.isInstance(object)
                  && !((SendingMessageActionTransition) object).getSourceParticipant().equals(participant)
                  && !((SendingMessageActionTransition) object).getTargetParticipant().equals(participant);
         }
      }).stream().map(transition -> new InternalActionTransition(new State(transition.getSourceState().getName()),
            new State(transition.getTargetState().getName()))).collect(Collectors.toList());
   }

   public static Collection<ReceiveActionTransition> findReceiveActionTransitions(
         final ChoreographySpecification choreographySpecification, final Participant participant) {

      return CollectionUtils.select(choreographySpecification.getTransitions(), new Predicate<Transition>() {
         @Override
         public boolean evaluate(final Transition object) {
            return SendingMessageActionTransition.class.isInstance(object)
                  && !((SendingMessageActionTransition) object).getSourceParticipant().equals(participant)
                  && ((SendingMessageActionTransition) object).getTargetParticipant().equals(participant);
         }
      }).stream()
            .map(transition -> new ReceiveActionTransition(new State(transition.getSourceState().getName()),
                  new State(transition.getTargetState().getName()),
                  new InputMessage(((SendingMessageActionTransition) transition).getMessage().getName())))
            .collect(Collectors.toList());
   }

   public static Collection<SendActionTransition> findSendActionTransitions(
         final ChoreographySpecification choreographySpecification, final Participant participant) {

      return CollectionUtils.select(choreographySpecification.getTransitions(), new Predicate<Transition>() {
         @Override
         public boolean evaluate(final Transition object) {
            return SendingMessageActionTransition.class.isInstance(object)
                  && ((SendingMessageActionTransition) object).getSourceParticipant().equals(participant)
                  && !((SendingMessageActionTransition) object).getTargetParticipant().equals(participant);
         }
      }).stream()
            .map(transition -> new SendActionTransition(new State(transition.getSourceState().getName()),
                  new State(transition.getTargetState().getName()),
                  new OutputMessage(((SendingMessageActionTransition) transition).getMessage().getName())))
            .collect(Collectors.toList());
   }

   // -----------------------------------------------------------------------

   /**
    * <p>
    * {@code ChoreographyProjectionUtils} instances should NOT be constructed in standard programming. Instead, the
    * class should be used statically.
    * </p>
    *
    * <p>
    * This constructor is public to permit tools that require a JavaBean instance to operate.
    * </p>
    */
   public ChoreographyProjectionUtils() {
      super();
   }

}
