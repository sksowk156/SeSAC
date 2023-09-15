package idolgroup


class IdolgroupManagement private constructor(){
    companion object {
        private var instance : IdolgroupManagement ?= null
        fun getIdolgroupManagement():IdolgroupManagement =
            instance?: mutex.withLock{

            }

    }
}