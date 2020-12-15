enum PathsLabel{
    Home = "/Home",
    Register = "/Register",
    Login = "/",
    ForgotPassword = "/ForgotPassword",
    Calls = "/Calls",
    DataItems = "/DataItems",
    Applications = "/Applcations",
    Panels = "/Panels",
    Details = "/Details"
}

enum ServicePathsLabel{
    Api = "http://localhost:8080/",
    Applications = "applications/",
    Calls = "calls/",
    DataItems = "items/",
    Institutions = "institutions/",
    Login = "login",
    Reviewers = "reviewers/",
    Sponsors = "sponsors/",
    Students = "students/",
}

enum SubServicesLable{
    ApplicationData = "data/",
    ApplicationReview = "applicationReview/",
    CallData = "dataInCall",
    Panels = "panels/",
    ReviewersInPanels = "reviewersInPanel/",
    StudentCV = "studentCV/",
}

enum StorageNames{
    activeMenuItem = "activeMenuItem"
}

export {PathsLabel, ServicePathsLabel, SubServicesLable, StorageNames};