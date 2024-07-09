POST /job -> can pass request body json type like that
{
    "fields": "jobId,employer,location,jobTitle,gender,salary,timestamp,yearOfEmployer,yearOfExperience,signingBonus,annualBonus,annualStockValue,additionalComments",
    "filters": {
        "salary[gte]": ["420000"],
        "jobTitle": ["Systems Engineer"],
        "gender": ["Female"]
    },
    "sorts": {        
        "jobId": "ASC",
        "location": "ASC",
        "employer": "ASC",
        "yearOfEmployer": "DESC"
    }
}

For salary[gte], it is only allow one item in array. 
Many item can add to fitler jobTitle and gender in array.
"jobTitle": ["Systems Engineer", "Junior Engineer"],

Sort fields can add any type of "jobId,employer,location,jobTitle,gender,salary,timestamp,yearOfEmployer,yearOfExperience,signingBonus,annualBonus,annualStockValue,additionalComments" with ASC or DESC.

