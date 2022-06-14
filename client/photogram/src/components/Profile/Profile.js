import React from 'react';
import ProfileHeader from './ProfileHeader';
import PhotoGallery from './PhotoGallery';
import './profile.scss'


function Profile() {

    return (
        <div id='profileContainer'>
            <ProfileHeader />
            <PhotoGallery />
        </div>
    )


}

export default Profile;