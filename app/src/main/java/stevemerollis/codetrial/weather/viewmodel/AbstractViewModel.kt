package stevemerollis.codetrial.weather.viewmodel

interface AbstractViewModel {

    interface State {
        object Init: State
        object Loading: State
    }
}