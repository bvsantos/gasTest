package pt.unl.fct.tourtuga.gas.service

import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.application.*
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.data.dao.DataInApplicationDAO
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationReviewDAO
import pt.unl.fct.tourtuga.gas.data.repository.*
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException
import java.time.LocalDate
import java.util.*

@Service
class ApplicationService(
        val applications: ApplicationRepository,
        val dataInApplications: DataInApplicationRepository,
        val applicationReviews: ApplicationReviewRepository,
        val reviewers: ReviewerRepository,
        val calls: CallRepository
) {

    fun getAllApplications(): List<ApplicationDAO> {
        var allApplications = applications.findAll();
        if (allApplications.isEmpty())
            throw HTTPNotFoundException("There are no Applications!");
        else
            return allApplications;
    }

    fun getApplicationById(id: Long): ApplicationDAO {
        return applications.findById(id).orElseThrow {
            HTTPNotFoundException("Application doesn't exist with id: $id")
        }
    }

    fun getApplicationsByStudentId(id: Long) : List<ApplicationDAO> {
        var allApplications = applications.findAllByStudentId(id);
        if (allApplications.isEmpty())
            throw HTTPNotFoundException("There are no Applications for that Student!");
        else
            return allApplications;
    }

    fun getApplicationsByReviewId(id: Long) : List<ApplicationDAO> {
        var allApplications = applications.findAllByReviewerId(id);
        if (allApplications.isEmpty())
            throw HTTPNotFoundException("There are no Applications for that Reviewer!");
        else
            return allApplications;
    }

    // Verificar se a call e o student existem
    fun createApplication(application: ApplicationDTO) {
        if (getApplication(application.id, true) != null) {
            applications.save(ApplicationDAO(application));
        }
    }

    fun updateApplication(application: ApplicationDTO) {
        if (getApplication(application.id, false) != null) {
            applications.findById(application.id).get().let {
                it.update(application);
                applications.save(it);
            };
        }
    }

    fun deleteApplication(id: Long) {
        if (getApplication(id, false) != null) {
            applications.deleteById(id);
        }
    }

    //------------------------Data_In_Application--------------------------------//

    fun getAllDataInApplicationById(id: Long): List<DataInApplicationDAO> {
        return dataInApplications.findAllDataApplicationByApplicationId(id);
    }

    /**
     * If the dataInApplications doesnt already exist, the application and the dataItem exists
     * creates a dataInApplication record
     */
    fun addDataToApplication(dataInApplication: DataInApplicationDTO) {
        if (getDataInApplication(dataInApplication.id, true) != null
                && getApplication(dataInApplication.applicationID, false) != null
                && getDataItem(dataInApplication.dataID, false) != null) {
            dataInApplications.save(DataInApplicationDAO(dataInApplication));
        }
    }

    fun deleteDataFromApplication(id: Long) {
        if (getDataInApplication(id, false) != null) {
            dataInApplications.deleteById(id);
        }
    }

    //-------------------------Application_Review---------------------------------//

    fun applicationReview(review: ApplicationReviewDTO) {
        //ao testar melhor, usar primeira linha
        //applicationReviews.findStudentIdAndReviewInfo(review.applicationId, review.reviewerId);
        var studentInstitutionId = applications.getStudentsInstitutionByApplicationId(review.applicationId).get().institutionId;
        var reviewerInstitutionId = reviewers.findById(review.reviewerId).get().institutionId;
        var closingDate = calls.findByApplicationId(review.applicationId).get().closingDate;

        getApplication(review.applicationId, false); // verifies if the application exists

        if (closingDate <= LocalDate.now())
            throw HTTPConflictException("Call needs to be closed in order to be reviewed!");
        else if (studentInstitutionId == reviewerInstitutionId)
            throw HTTPConflictException("Reviewer can't review an application from a student in the sam Institution as him!");
        else if (getApplicationReview(review.id, true) != null)
            applicationReviews.save(ApplicationReviewDAO(review));
    }

    fun getApplicationReviewedByReviewerId(id:Long) : List<ApplicationReviewDAO> {
        var allApplications = applicationReviews.findAllByReviewerId(id);
        if (allApplications.isEmpty())
            throw HTTPNotFoundException("There are no Applications Reviewed for that Reviewer!");
        else
            return allApplications;
    }

    fun getReviewsByApplicationId(id:Long) : List<ApplicationReviewDAO> {
        var allApplications = applicationReviews.findAllByApplicationId(id);
        if (allApplications.isEmpty())
            throw HTTPNotFoundException("There are no Applications Reviewed for that Reviewer!");
        else
            return allApplications;
    }

    //--------------------------Private--------------------------------//

    /**
     * Returns an application with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getApplication(id: Long, isCreating: Boolean) {
        var app = applications.findById(id);
        if (!isCreating) {
            app.orElseThrow {
                HTTPNotFoundException("Application doesn't exist with id: $id");
            };
        } else if (app != null) {
            HTTPConflictException("Application already exists with id: $id");
        }
    }

    /**
     * Returns a dataInApplication with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getDataInApplication(id: Long, isCreating: Boolean) {
        var dataInApp = dataInApplications.findById(id);
        if (!isCreating) {
            dataInApp.orElseThrow {
                HTTPNotFoundException("DataInApplication doesn't exist with id: $id");
            };
        } else if (dataInApp != null) {
            HTTPConflictException("Data in Application already exists with id: $id");
        }
    }

    /**
     * Returns a data item with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getDataItem(id: Long, isCreating: Boolean) {
        var dataItem = dataInApplications.findByDataItemId(id);
        if (!isCreating) {
            dataItem.orElseThrow {
                HTTPNotFoundException("DataInApplication doesn't exist with id: $id");
            };
        } else if (dataItem != null) {
            HTTPConflictException("Data in Application already exists with id: $id");
        }
    }

    /**
     * Returns an applicationReview with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getApplicationReview(id: Long, isCreating: Boolean) {
        var appReview = applicationReviews.findById(id);
        if (!isCreating) {
            appReview.orElseThrow {
                HTTPNotFoundException("Application Review doesn't exist with id: $id");
            };
        } else if (appReview != null) {
            HTTPConflictException("Application Review already exists with id: $id");
        }
    }
}