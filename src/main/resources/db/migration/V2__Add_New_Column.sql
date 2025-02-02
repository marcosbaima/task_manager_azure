CREATE TABLE Tasks (
  TaskID INT IDENTITY(1,1) PRIMARY KEY,
  Title NVARCHAR(255) NOT NULL,
  Description NVARCHAR(MAX),
  IsCompleted BIT NOT NULL DEFAULT 0,
  CreatedAt DATETIME DEFAULT GETDATE()
);
