/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

/**
 *
 * @author ericr
 */
public enum TimerState {
    NOT_STARTED {
        @Override
        public String toString() {
            return("NOT_STARTED");
        }
    },
    RUNNING {
        @Override
        public String toString() {
            return("RUNNING");
        }
    },
    PAUSED {
        @Override
        public String toString() {
            return("PAUSED");
        }
    },
    STOPPED {
        @Override
        public String toString() {
            return("STOPPED");
        }
    }
}
