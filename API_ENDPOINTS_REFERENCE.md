# Social Media API Endpoints Reference

## Base URL
```
http://localhost:8080/api/v1
```

---

## 📋 Comments Enhancement
### Endpoints:
- `GET /comments/{id}` - Get comment by ID
- `GET /comments/post/{postId}` - Get all comments for a post (paginated)
- `POST /comments` - Create new comment
- `PUT /comments/{id}` - Update comment
- `DELETE /comments/{id}` - Delete comment

### Request/Response:
```json
{
  "id": "string",
  "postId": "string",
  "authorId": "string",
  "authorUsername": "string",
  "authorProfilePictureUrl": "string",
  "content": "string",
  "likesCount": 0,
  "createdAt": "2026-08-06T23:32:23.835Z",
  "editedAt": "2026-08-06T23:32:23.835Z",
  "updatedAt": "2026-08-06T23:32:23.835Z"
}
```

---

## 🔄 Retweets
### Endpoints:
- `POST /retweets` - Create retweet
- `DELETE /retweets/{id}` - Delete retweet
- `GET /retweets/post/{postId}` - Get retweets for a post
- `GET /retweets/count/{postId}` - Get retweet count

### Request/Response:
```json
{
  "id": "string",
  "postId": "string",
  "userId": "string",
  "createdAt": "2026-08-06T23:32:23.835Z"
}
```

---

## 📌 Bookmarks
### Endpoints:
- `POST /bookmarks` - Bookmark a post
- `DELETE /bookmarks/{id}` - Remove bookmark
- `GET /bookmarks` - Get all bookmarks (paginated)

### Request/Response:
```json
{
  "id": "string",
  "postId": "string",
  "userId": "string",
  "createdAt": "2026-08-06T23:32:23.835Z"
}
```

---

## 📸 Media Upload
### Endpoints:
- `POST /media/upload` - Upload media file
- `GET /media/{id}` - Get media by ID
- `GET /media/user/{userId}` - Get user's uploaded media (paginated)
- `DELETE /media/{id}` - Delete media

### File Validation:
- Max size: 50MB
- Allowed types: image/jpeg, image/png, image/gif, video/mp4

### Response:
```json
{
  "id": "string",
  "fileName": "string",
  "fileType": "string",
  "fileSize": 1024,
  "url": "string",
  "uploadedBy": "string",
  "createdAt": "2026-08-06T23:32:23.835Z"
}
```

---

## #️⃣ Hashtags
### Endpoints:
- `POST /hashtags` - Create hashtag (auto-processed)
- `GET /hashtags/search` - Search hashtags
- `GET /hashtags/trending` - Get trending hashtags (paginated)
- `GET /hashtags/top-trending` - Get top 10 trending hashtags

### Response:
```json
{
  "id": "string",
  "tag": "string",
  "usageCount": 0,
  "lastUsedAt": "2026-08-06T23:32:23.835Z"
}
```

---

## 📖 Stories (with 24-hour TTL)
### Endpoints:
- `POST /stories` - Create story
- `GET /stories/my-stories` - Get current user's stories
- `GET /stories/user/{userId}` - Get user's stories
- `DELETE /stories/{id}` - Delete story

### Response:
```json
{
  "id": "string",
  "authorId": "string",
  "content": "string",
  "mediaUrl": "string",
  "createdAt": "2026-08-06T23:32:23.835Z",
  "expiresAt": "2026-08-07T23:32:23.835Z"
}
```

**Note**: Stories automatically expire after 24 hours via MongoDB TTL index.

---

## 🔍 Search
### Endpoints:
- `GET /search/users` - Search users by username/fullName
- `GET /search/posts` - Search posts by content
- `GET /search/hashtags` - Search hashtags
- `GET /search` - Global search (all types)

### Query Parameters:
- `query` (required) - Search term
- `pageNumber` (optional, default: 0)
- `pageSize` (optional, default: 10)

### Response:
```json
{
  "users": [...],
  "posts": [...],
  "hashtags": [...]
}
```

---

## 🔥 Trending
### Endpoints:
- `GET /trending/posts` - Get trending posts (paginated)
- `GET /trending/posts/top` - Get top trending posts
- `GET /trending/hashtags` - Get trending hashtags (paginated)
- `GET /trending/hashtags/top` - Get top trending hashtags

### Scoring Algorithm:
- Posts: Likes × 2 (calculated from last 7 days)
- Hashtags: Usage count (all time)

---

## 💬 Mentions
### Automatic Processing:
- Mentions are extracted from post/comment content
- Pattern: `@username`
- Stored in post/comment records
- Available for notification triggers

### Utility Methods:
- `MentionExtractorUtil.extractMentions(String content)`
- `MentionExtractorUtil.hasMentions(String content)`
- `MentionExtractorUtil.replaceMentions(String content, String format)`

---

## 🔔 Notifications
### Endpoints:
- `GET /notifications` - Get user's notifications (paginated)
- `GET /notifications/unread/count` - Get unread notification count
- `PUT /notifications/{id}/read` - Mark notification as read
- `PUT /notifications/all/read` - Mark all notifications as read
- `DELETE /notifications/{id}` - Delete notification

### Notification Types:
- `LIKE` - User liked your post/comment
- `COMMENT` - User commented on your post
- `RETWEET` - User retweeted your post
- `FOLLOW` - User followed you
- `MENTION` - User mentioned you

### Response:
```json
{
  "id": "string",
  "recipientId": "string",
  "type": "LIKE|COMMENT|RETWEET|FOLLOW|MENTION",
  "sourceUserId": "string",
  "postId": "string",
  "commentId": "string",
  "createdAt": "2026-08-06T23:32:23.835Z",
  "isRead": false
}
```

---

## ⏱️ Rate Limiting
### Configuration:
- Limit: 100 requests per minute per user
- Applies to: All `/api/**` endpoints
- Window: 60 seconds (auto-resets)

### Response on Limit Exceeded:
```
HTTP 429 Too Many Requests
"Rate limit exceeded: 100 requests per minute"
```

### User Identification:
- Via JWT token in `Authorization: Bearer {token}` header
- Or via `X-User-Id` header

---

## 📋 Content Moderation (Reports)
### Endpoints:
- `POST /reports` - Create report
- `GET /reports` - Get pending reports (admin)
- `GET /reports?status=PENDING|REVIEWED|RESOLVED` - Get reports by status (admin)
- `GET /reports/{id}` - Get report details (admin)
- `PUT /reports/{id}/review` - Review report (admin)
- `PUT /reports/{id}/resolve` - Resolve report (admin)

### Report Content Types:
- `POST` - Report a post
- `COMMENT` - Report a comment
- `USER` - Report a user

### Report Status:
- `PENDING` - Initial status
- `REVIEWED` - Reviewed by admin
- `RESOLVED` - Issue resolved

### Request:
```json
{
  "contentType": "POST|COMMENT|USER",
  "contentId": "string",
  "reason": "string",
  "description": "string"
}
```

### Response:
```json
{
  "id": "string",
  "reportedBy": "string",
  "contentType": "POST|COMMENT|USER",
  "contentId": "string",
  "reason": "string",
  "description": "string",
  "status": "PENDING|REVIEWED|RESOLVED",
  "createdAt": "2026-08-06T23:32:23.835Z",
  "reviewedAt": "2026-08-06T23:32:23.835Z",
  "reviewedBy": "string",
  "resolution": "string"
}
```

---

## 💌 Enhanced Direct Messaging
### Enhanced Features:
- Read receipts via `isRead` and `readAt` fields
- Soft delete via `deletedAt` field
- Separate deletion tracking for sender/receiver

### Endpoints (via Chat):
- Mark message as read
- Soft delete message
- Standard messaging endpoints

### Message Model:
```json
{
  "id": "string",
  "conversationId": "string",
  "senderId": "string",
  "content": "string",
  "sentAt": "2026-08-06T23:32:23.835Z",
  "seenStatus": "PENDING|SEEN",
  "isRead": true,
  "readAt": "2026-08-06T23:32:23.835Z",
  "deletedBySender": false,
  "deletedByReceiver": false,
  "deletedAt": "2026-08-06T23:32:23.835Z"
}
```

---

## 👤 User Verification
### Enhanced User Model:
- New field: `isVerified` (Boolean)

### Endpoints:
- Update user verification status (admin only)

### Response:
```json
{
  "id": "string",
  "username": "string",
  "email": "string",
  "fullName": "string",
  "isVerified": true,
  ...
}
```

---

## 🛠️ Common Parameters

### Pagination:
```
?pageNumber=0&pageSize=10
```

### Authentication:
```
Authorization: Bearer {jwtToken}
```

### Headers:
```
Content-Type: application/json
Authorization: Bearer {token}
```

---

## 📊 Post Model Enhanced Fields
```json
{
  "id": "string",
  "authorId": "string",
  "content": "string",
  "likesCount": 0,
  "commentsCount": 0,
  "retweetsCount": 0,
  "bookmarksCount": 0,
  "createdAt": "2026-08-06T23:32:23.835Z"
}
```

---

## ✅ Implementation Complete

All 14 features are fully implemented with complete REST API endpoints, proper validation, error handling, and MongoDB integration.

