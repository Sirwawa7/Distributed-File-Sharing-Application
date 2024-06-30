The distributed file sharing application is designed to facilitate decentralized file sharing 
across multiple devices and locations. This project provides a hands-on experience in 
designing and implementing distributed systems, addressing practical challenges in file 
sharing and collaboration.
Project Objectives
- Develop a decentralized file sharing system.
- Implement user authentication.
- Enable file upload and download functionalities.
- Ensure file synchronization across distributed nodes.
- Implement search and discovery of files.
- Provide security and encryption.
- Ensure scalability and fault tolerance.
Architecture Diagram
Components
1. User Authentication:
 - Handles user login and registration.
 - Ensures secure access to the application.
2. File Upload and Download:
 - Allows users to upload and download files.
3. File Synchronization:
 - Keeps files up-to-date across multiple devices.
4. Search and Discovery:
 - Enables users to search and discover files.
5. Security and Encryption:
 - Ensures files are securely stored and transferred.
6. Scalability and Fault Tolerance:
 - Ensures the application can scale and handle faults.
Decentralization
- The system is designed to distribute files across multiple nodes.
- Nodes communicate and synchronize files using predefined protocols.
- Redundancy is built in to handle node failures and ensure data availability.
Implementation
Backend
The backend of the application is implemented using Java. Key classes and their 
responsibilities are detailed below.
Frontend
The frontend is implemented using HTML, CSS, and JavaScript.
API Endpoints
1. /login: Handles user login.
 - Method: POST
 - Request: username, password
 - Response: Login successful or Invalid credentials
2. /upload: Handles file upload.
 - Method: POST
 - Request: file
 - Response: File uploaded successfully
3. /download: Handles file download.
 - Method: GET
 - Request: fileName
 - Response: File data
Testing
Test Plan
- Test user authentication.
- Test file upload functionality.
- Test file download functionality.
Test Cases - User Authentication
Login
File Upload & File Download
Challenges Faced
- Challenge 1: Handling multiple HTTP methods in HttpServer.
 - Solution: Detailed logging and verifying methods in handlers.
- Challenge 2: Ensuring secure file upload and download.
 - Solution: Implementing proper input validation and file path resolution.
- Challenge 3: Synchronizing files across distributed nodes.
 - Solution: Implementing a synchronization protocol to ensure consistency.
Design Decisions
1. Choice of Technologies:
 - Backend: Java was chosen for its robustness and extensive libraries for networking and 
concurrency.
 - Frontend: HTML, CSS, and JavaScript were used for simplicity and compatibility with web 
technologies.
 - HTTP Server: `com.sun.net.httpserver.HttpServer` was used for its simplicity and ease 
of integration with Java applications.
2. Decentralization and Synchronization:
 - Synchronization Protocol: A protocol was designed to ensure files are consistently 
synchronized across all nodes.
 - Conflict Resolution: Mechanisms were implemented to handle conflicts when multiple 
nodes attempt to update the same file simultaneously.
3. File Handling:
 - Storage Location: Files are stored in a designated directory on the server. This simplifies 
access and management.
 - Path Resolution: Absolute paths were used to avoid issues with relative path resolution, 
ensuring that files are correctly located and accessed.
4. User Authentication:
 - A simple username-password authentication mechanism was implemented to restrict 
access to the application.
 - This could be extended with more advanced authentication mechanisms like OAuth in 
future iterations.
Significant Findings
1. HTTP Method Handling:
 - Proper handling of HTTP methods is crucial. Misconfiguration can lead to method not 
allowed errors.
 - Detailed logging helps in identifying and resolving such issues efficiently.
2. File Upload and Download:
 - Ensuring correct handling of file streams is critical to prevent data corruption.
 - MIME types and content headers play a significant role in how browsers and clients 
handle file uploads and downloads.
3. Security Considerations:
 - Input validation is essential to prevent path traversal attacks and other security 
vulnerabilities.
 - Encrypting files before storage could be a potential enhancement to increase security.
4. Distributed Nature:
 - The distributed nature of the system introduced complexities in ensuring data 
consistency and availability.
 - Redundancy and proper synchronization protocols are essential to address these 
challenges.
Observations
- Performance: The application performs well under light to moderate load. Stress testing 
would be required to evaluate performance under heavy load.
- Scalability: While the current implementation is suitable for small deployments, further 
work would be needed to ensure scalability for larger user bases and datasets.
-User Experience: The current UI is functional but basic. Enhancing the user interface could 
improve user experience significantly.
Conclusion
The distributed file sharing application successfully implements a decentralized system for 
file sharing. It provides secure and efficient mechanisms for user authentication, file upload, 
and download, addressing key aspects of distributed systems.
References
- Coulouris, G., Dollimore, J., & Kindberg, T. (2011). *Distributed Systems: Concepts and 
Design*.
- Harold, E. R. (2004). *Java Network Programming*.
- Online tutorials and documentation for relevant frameworks and libraries
